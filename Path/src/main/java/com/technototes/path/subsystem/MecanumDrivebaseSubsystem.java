package com.technototes.path.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.followers.TrajectoryFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.technototes.library.hardware.HardwareDevice;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.path.subsystem.MecanumDriveConstants.*;
import com.technototes.path.util.LynxModuleUtil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class MecanumDrivebaseSubsystem extends MecanumDrive implements Subsystem {
    public double speed = 1;

    public enum Mode {
        IDLE,
        TURN,
        FOLLOW_TRAJECTORY
    }

    protected NanoClock clock;

    public Mode mode;

    protected PIDFController turnController;
    protected MotionProfile turnProfile;
    protected double turnStart;

    protected TrajectoryVelocityConstraint velConstraint;
    protected TrajectoryAccelerationConstraint accelConstraint;
    protected TrajectoryFollower follower;

    protected LinkedList<Pose2d> poseHistory;

    protected EncodedMotor<DcMotorEx> leftFront, leftRear, rightRear, rightFront;
    protected List<DcMotorEx> motors;
    protected IMU imu;

    protected VoltageSensor batteryVoltageSensor;

    protected Pose2d lastPoseOnTurn;

    public final double TICKS_PER_REV,MAX_RPM;

    public final boolean RUN_USING_ENCODER;

    public final PIDFCoefficients MOTOR_VELO_PID;

    public final double WHEEL_RADIUS, GEAR_RATIO, TRACK_WIDTH, kV, kA, kStatic;

    public final double MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL;

    public final PIDCoefficients TRANSLATIONAL_PID, HEADING_PID;

    public final double LATERAL_MULTIPLIER, VX_WEIGHT, VY_WEIGHT, OMEGA_WEIGHT;

    public final int POSE_HISTORY_LIMIT;

    public MecanumDrivebaseSubsystem(EncodedMotor<DcMotorEx> fl, EncodedMotor<DcMotorEx> fr,
                                     EncodedMotor<DcMotorEx> rl, EncodedMotor<DcMotorEx> rr,
                                     IMU i, MecanumDriveConstants c) {
        super(c.getDouble(KV.class), c.getDouble(KA.class), c.getDouble(KStatic.class), c.getDouble(TrackWidth.class), c.getDouble(LateralMult.class));

        TICKS_PER_REV = c.getDouble(TicksPerRev.class);
        MAX_RPM = c.getDouble(MaxRPM.class);

        RUN_USING_ENCODER = c.getBoolean(UseDriveEncoder.class);

        MOTOR_VELO_PID = c.getPIDF(MotorVeloPID.class);

        WHEEL_RADIUS = c.getDouble(WheelRadius.class);
        GEAR_RATIO = c.getDouble(GearRatio.class);
        TRACK_WIDTH = c.getDouble(TrackWidth.class);
        kV = c.getDouble(KV.class);
        kA = c.getDouble(KA.class);
        kStatic = c.getDouble(KStatic.class);

        MAX_VEL = c.getDouble(MaxVelo.class);
        MAX_ACCEL = c.getDouble(MaxAccel.class);
        MAX_ANG_VEL = c.getDouble(MaxAngleVelo.class);
        MAX_ANG_ACCEL = c.getDouble(MaxAngleAccel.class);

        TRANSLATIONAL_PID = c.getPID(TransPID.class);
        HEADING_PID = c.getPID(HeadPID.class);

        LATERAL_MULTIPLIER = c.getDouble(LateralMult.class);
        VX_WEIGHT = c.getDouble(VXWeight.class);
        VY_WEIGHT = c.getDouble(VYWeight.class);
        OMEGA_WEIGHT = c.getDouble(OmegaWeight.class);

        POSE_HISTORY_LIMIT = c.getInt(PoseLimit.class);


        clock = NanoClock.system();

        mode = Mode.IDLE;

        turnController = new PIDFController(HEADING_PID);
        turnController.setInputBounds(0, 2 * Math.PI);

        velConstraint = new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(MAX_ANG_VEL),
                new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
        ));
        accelConstraint = new ProfileAccelerationConstraint(MAX_ACCEL);
        follower = new HolonomicPIDVAFollower(TRANSLATIONAL_PID, TRANSLATIONAL_PID, HEADING_PID,
                new Pose2d(0.5, 0.5, Math.toRadians(5.0)), 0.5);

        poseHistory = new LinkedList<>();

        LynxModuleUtil.ensureMinimumFirmwareVersion(HardwareDevice.hardwareMap);

        batteryVoltageSensor = HardwareDevice.hardwareMap.voltageSensor.iterator().next();

        for (LynxModule module : HardwareDevice.hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        imu = i.radians().initialize();

        rightFront = fr;
        leftFront = fl;
        leftRear = rl;
        rightRear = rr;

        motors = Arrays.asList(leftFront.getDevice(), leftRear.getDevice(), rightRear.getDevice(), rightFront.getDevice());


        for (DcMotor motor : motors) {
            MotorConfigurationType motorConfigurationType = motor.getMotorType().clone();
            motorConfigurationType.setAchieveableMaxRPMFraction(1.0);
            motor.setMotorType(motorConfigurationType);
        }

        if (RUN_USING_ENCODER) {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (RUN_USING_ENCODER && MOTOR_VELO_PID != null) {
            setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        }

        setPoseEstimate(new Pose2d());
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose) {
        return new TrajectoryBuilder(startPose, velConstraint, accelConstraint);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed) {
        return new TrajectoryBuilder(startPose, reversed, velConstraint, accelConstraint);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, double startHeading) {
        return new TrajectoryBuilder(startPose, startHeading, velConstraint, accelConstraint);
    }

    public void turnAsync(double angle) {
        double heading = getPoseEstimate().getHeading();

        lastPoseOnTurn = getPoseEstimate();

        turnProfile = MotionProfileGenerator.generateSimpleMotionProfile(
                new MotionState(heading, 0, 0, 0),
                new MotionState(heading + angle, 0, 0, 0),
                MAX_ANG_VEL,
                MAX_ANG_ACCEL
        );

        turnStart = clock.seconds();
        mode = Mode.TURN;
    }

    public void turn(double angle) {
        turnAsync(angle);
        waitForIdle();
    }

    public void followTrajectoryAsync(Trajectory trajectory) {
        follower.followTrajectory(trajectory);
        mode = Mode.FOLLOW_TRAJECTORY;
    }

    public void followTrajectory(Trajectory trajectory) {
        followTrajectoryAsync(trajectory);
        waitForIdle();
    }

    public Pose2d getLastError() {
        switch (mode) {
            case FOLLOW_TRAJECTORY:
                return follower.getLastError();
            case TURN:
                return new Pose2d(0, 0, turnController.getLastError());
            case IDLE:
                return new Pose2d();
        }
        throw new AssertionError();
    }

    public void update() {
        updatePoseEstimate();

        Pose2d currentPose = getPoseEstimate();
        Pose2d lastError = getLastError();

        poseHistory.add(currentPose);

        if (POSE_HISTORY_LIMIT > -1 && poseHistory.size() > POSE_HISTORY_LIMIT) {
            poseHistory.removeFirst();
        }

        switch (mode) {
            case IDLE:
                // do nothing
                break;
            case TURN: {
                double t = clock.seconds() - turnStart;

                MotionState targetState = turnProfile.get(t);

                turnController.setTargetPosition(targetState.getX());

                double correction = turnController.update(currentPose.getHeading());

                double targetOmega = targetState.getV();
                double targetAlpha = targetState.getA();
                setDriveSignal(new DriveSignal(new Pose2d(
                        0, 0, targetOmega + correction
                ), new Pose2d(
                        0, 0, targetAlpha
                )));

                Pose2d newPose = lastPoseOnTurn.copy(lastPoseOnTurn.getX(), lastPoseOnTurn.getY(), targetState.getX());

                if (t >= turnProfile.duration()) {
                    mode = Mode.IDLE;
                    setDriveSignal(new DriveSignal());
                }

                break;
            }
            case FOLLOW_TRAJECTORY: {
                setDriveSignal(follower.update(currentPose, getPoseVelocity()));

                Trajectory trajectory = follower.getTrajectory();

                if (!follower.isFollowing()) {
                    mode = Mode.IDLE;
                    setDriveSignal(new DriveSignal());
                }

                break;
            }
        }

    }

    public void waitForIdle() {
        while (!Thread.currentThread().isInterrupted() && isBusy()) {
            update();
        }
    }

    public boolean isBusy() {
        return mode != Mode.IDLE;
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotorEx motor : motors) {
            motor.setMode(runMode);
        }
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (DcMotorEx motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients) {
        PIDFCoefficients compensatedCoefficients = new PIDFCoefficients(
                coefficients.p, coefficients.i, coefficients.d,
                coefficients.f * 12 / batteryVoltageSensor.getVoltage()
        );
        for (DcMotorEx motor : motors) {
            motor.setPIDFCoefficients(runMode, compensatedCoefficients);
        }
    }

    public void setWeightedDrivePower(Pose2d drivePower) {
        Pose2d vel = drivePower;

        if (Math.abs(drivePower.getX()) + Math.abs(drivePower.getY())
                + Math.abs(drivePower.getHeading()) > 1) {
            // re-normalize the pwers according to the weightso
            double denom = VX_WEIGHT * Math.abs(drivePower.getX())
                    + VY_WEIGHT * Math.abs(drivePower.getY())
                    + OMEGA_WEIGHT * Math.abs(drivePower.getHeading());

            vel = new Pose2d(
                    VX_WEIGHT * drivePower.getX(),
                    VY_WEIGHT * drivePower.getY(),
                    OMEGA_WEIGHT * drivePower.getHeading()
            ).div(denom);
        }

        setDrivePower(vel);
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        List<Double> wheelPositions = new ArrayList<>();
        for (DcMotorEx motor : motors) {
            wheelPositions.add(MecanumDriveConstants.encoderTicksToInches(motor.getCurrentPosition(), WHEEL_RADIUS, GEAR_RATIO, TICKS_PER_REV));
        }
        return wheelPositions;
    }

    @Override
    public List<Double> getWheelVelocities() {
        List<Double> wheelVelocities = new ArrayList<>();
        for (DcMotorEx motor : motors) {
            wheelVelocities.add(MecanumDriveConstants.encoderTicksToInches(motor.getVelocity(), WHEEL_RADIUS, GEAR_RATIO, TICKS_PER_REV));
        }
        return wheelVelocities;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        leftFront.setSpeed(v);
        leftRear.setSpeed(v1);
        rightRear.setSpeed(v2);
        rightFront.setSpeed(v3);
    }

    @Override
    public double getRawExternalHeading() {
        return imu.getAngularOrientation().firstAngle;
    }

    @Override
    public Double getExternalHeadingVelocity() {
        return (double) imu.getAngularVelocity().zRotationRate;
    }
}

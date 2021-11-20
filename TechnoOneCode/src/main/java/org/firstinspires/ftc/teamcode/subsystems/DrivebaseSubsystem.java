package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.util.Alliance;
import com.technototes.path.subsystem.MecanumDriveConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.FRONT_SENSOR_DISTANCE;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.LEFT_SENSOR_DISTANCE;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.RIGHT_SENSOR_DISTANCE;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.TIP_TOLERANCE;

@SuppressWarnings("unused")

public class DrivebaseSubsystem extends MecanumDrivebaseSubsystem implements Supplier<Pose2d> {

    @Config
    public abstract static class DriveConstants implements MecanumDriveConstants {

        @TicksPerRev
        public static final double TICKS_PER_REV = 28;

        @MaxRPM
        public static final double MAX_RPM = 6000;

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = true;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(25, 0, 4,
                MecanumDriveConstants.getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        @WheelRadius
        public static double WHEEL_RADIUS = 1.88976; // in
        @GearRatio
        public static double GEAR_RATIO = 1 / 13.7; // output (wheel) speed / input (motor) speed
        @TrackWidth
        public static double TRACK_WIDTH = 9.5; // in
        @KV
        public static double kV = 1.0 / MecanumDriveConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);
        @KA
        public static double kA = 0;
        @KStatic
        public static double kStatic = 0;

        @MaxVelo
        public static double MAX_VEL = 60;
        @MaxAccel
        public static double MAX_ACCEL = 50;
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(180);
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(120);

        @TransPID
        public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);
        @HeadPID
        public static PIDCoefficients HEADING_PID = new PIDCoefficients(8, 0, 0);

        @LateralMult
        public static double LATERAL_MULTIPLIER = 1;

        @VXWeight
        public static double VX_WEIGHT = 1;
        @VYWeight
        public static double VY_WEIGHT = 1;
        @OmegaWeight
        public static double OMEGA_WEIGHT = 1;

        @PoseLimit
        public static int POSE_HISTORY_LIMIT = 100;

        public static double LEFT_SENSOR_DISTANCE = 65.5;
        public static double RIGHT_SENSOR_DISTANCE = 65.5;
        public static double FRONT_SENSOR_DISTANCE = 65.5;

        public static double TIP_TOLERANCE = Math.toRadians(5);

    }

    public RangeSensor left, right, front;
//    protected FtcDashboard dashboard;

    public float xOffset, yOffset;

    public DrivebaseSubsystem(EncodedMotor<DcMotorEx> fl, EncodedMotor<DcMotorEx> fr,
                              EncodedMotor<DcMotorEx> rl, EncodedMotor<DcMotorEx> rr,
                              IMU i, RangeSensor l, RangeSensor r, RangeSensor f) {
        super(fl, fr, rl, rr, i, () -> DriveConstants.class);

        left = l;
        right = r;
        front = f;

        resetGyro();
//        dashboard = FtcDashboard.getInstance();
//        dashboard.setTelemetryTransmissionInterval(25);
    }

    public DrivebaseSubsystem(Hardware hardware){
        this(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor, hardware.imu, hardware.leftRangeSensor, hardware.rightRangeSensor, hardware.frontRangeSensor);
    }


    public void relocalizeCyclePose(Alliance alliance){
        //to make sure all sensors are valid
        if(front.getSensorValue() > 90 || ((Alliance.RED == alliance) ? right.getSensorValue() : left.getSensorValue()) > 10) return;
        setPoseEstimate(new Pose2d(
                FRONT_SENSOR_DISTANCE-front.getSensorValue(),
                alliance == Alliance.RED ? right.getSensorValue()-RIGHT_SENSOR_DISTANCE : LEFT_SENSOR_DISTANCE -left.getSensorValue(),
                getExternalHeading()));
    }


    public void relocalizeDuckPose(Alliance alliance){
        //to make sure all sensors are valid
        if(front.getSensorValue() > 10 || left.getSensorValue() > 10) return;
        setPoseEstimate(new Pose2d(
                alliance == Alliance.RED ? front.getSensorValue()-FRONT_SENSOR_DISTANCE : left.getSensorValue()-LEFT_SENSOR_DISTANCE,
                alliance == Alliance.RED ? left.getSensorValue()-LEFT_SENSOR_DISTANCE : FRONT_SENSOR_DISTANCE-front.getSensorValue(),
                getExternalHeading()));
    }

    public void resetGyro(){
        xOffset = imu.getAngularOrientation().secondAngle;
        yOffset = imu.getAngularOrientation().thirdAngle;
    }

    public void setSafeDrivePower(Pose2d raw){
        float x = 0, y = 0, adjX = xOffset-imu.getAngularOrientation().secondAngle, adjY = imu.getAngularOrientation().thirdAngle-yOffset;
        if(Math.abs(adjY) > TIP_TOLERANCE) y = adjY;
        if(Math.abs(adjX) > TIP_TOLERANCE) x = adjX;
//        setWeightedDrivePower(raw.plus(new Pose2d(x>0 ? Math.max(x-TIP_TOLERANCE, 0) : Math.min(x+TIP_TOLERANCE, 0), y>0 ? Math.max(y-TIP_TOLERANCE, 0) : Math.min(y+TIP_TOLERANCE, 0), 0)));
        setWeightedDrivePower(raw.plus(new Pose2d(x*2, y*2, 0)));
    }


    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }
}

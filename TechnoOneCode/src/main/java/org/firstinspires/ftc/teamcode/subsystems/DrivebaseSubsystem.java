package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.path.subsystem.MecanumDriveConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.teamcode.Hardware;

import java.util.function.Supplier;

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
        public static double GEAR_RATIO = 1 / 19.2; // output (wheel) speed / input (motor) speed
        @TrackWidth
        public static double TRACK_WIDTH = 11; // in
        @KV
        public static double kV = 1.0 / MecanumDriveConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);
        @KA
        public static double kA = 0;
        @KStatic
        public static double kStatic = 0;

        @MaxVelo
        public static double MAX_VEL = 50;
        @MaxAccel
        public static double MAX_ACCEL = 25;
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(80);
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(40);

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

    }

    public RangeSensor left, right;
//    protected FtcDashboard dashboard;


    public DrivebaseSubsystem(EncodedMotor<DcMotorEx> fl, EncodedMotor<DcMotorEx> fr,
                              EncodedMotor<DcMotorEx> rl, EncodedMotor<DcMotorEx> rr,
                              IMU i, RangeSensor l, RangeSensor r) {
        super(fl, fr, rl, rr, i, () -> DriveConstants.class);

        left = l;
        right = r;

//        dashboard = FtcDashboard.getInstance();
//        dashboard.setTelemetryTransmissionInterval(25);
    }

    public DrivebaseSubsystem(){
        this(Hardware.flDriveMotor, Hardware.frDriveMotor, Hardware.rlDriveMotor, Hardware.rrDriveMotor, Hardware.imu, Hardware.leftRangeSensor, Hardware.rightRangeSensor);
    }


    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }
}

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.technototes.library.hardware.motor.EncodedMotorGroup;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.subsystem.TankConstants;
import com.technototes.path.subsystem.TankDrivebaseSubsystem;

public class PhoenixDriveBase extends MecanumDrivebaseSubsystem {
    // TODO: Fill this in
    public static MecanumConstants constants;

    Config
    public abstract static class DriveConstants implements MecanumConstants {

        @TicksPerRev
        public static final double TICKS_PER_REV = 28;

        @MaxRPM
        public static final double MAX_RPM = 6000;

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = true;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(20, 0, 3,
                MecanumConstants.getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        @WheelRadius
        public static double WHEEL_RADIUS = 1.88976; // in
        @GearRatio
        public static double GEAR_RATIO = 1 / 19.2; // output (wheel) speed / input (motor) speed
        @TrackWidth
        public static double TRACK_WIDTH = 10; // in
        @WheelBase
        public static double WHEEL_BASE = 8.5; // in
        @KV
        public static double kV = 1.0 / MecanumConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);
        @KA
        public static double kA = 0;
        @KStatic
        public static double kStatic = 0;

        @MaxVelo
        public static double MAX_VEL = 60;
        @MaxAccel
        public static double MAX_ACCEL = 35;
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(180);
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(90);

        @TransPID
        public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);
        @HeadPID
        public static PIDCoefficients HEADING_PID = new PIDCoefficients(8, 0, 0);

        @LateralMult
        public static double LATERAL_MULTIPLIER = 1.14; // Lateral position is off by 14%

        @VXWeight
        public static double VX_WEIGHT = 1;
        @VYWeight
        public static double VY_WEIGHT = 1;
        @OmegaWeight
        public static double OMEGA_WEIGHT = 1;

        @PoseLimit
        public static int POSE_HISTORY_LIMIT = 100;

    }

    public PhoenixDriveBase (EncodedMotorGroup<DcMotorEx> frontLeft, EncodedMotorGroup<DcMotorEx> frontRight, EncodedMotorGroup<DcMotorEx> backLeft, EncodedMotorGroup<DcMotorEx> backRight, IMU i){
        super(frontLeft, frontRight, backLeft, backRight, i, constants, null);
    }
}

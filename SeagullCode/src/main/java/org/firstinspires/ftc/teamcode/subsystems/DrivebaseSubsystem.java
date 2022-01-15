package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.logger.Loggable;
import com.technototes.library.logger.Log;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import java.util.function.Supplier;

@SuppressWarnings("unused")

public class DrivebaseSubsystem extends MecanumDrivebaseSubsystem implements Supplier<Pose2d>, Loggable {

    @Config
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

    //@Log.Number (name = "Front Range Sensor")
    //public Rev2MDistanceSensor front_distance;
    //@Log.Number (name = "Left Range Sensor")
    //public Rev2MDistanceSensor left_distance;
    //@Log.Number (name = "Right Range Sensor")
    //public Rev2MDistanceSensor right_distance;

    private static final boolean ENABLE_POSE_DIAGNOSTICS = true;

    @Log (name="Pose2d: ")
    public String poseDisplay = ENABLE_POSE_DIAGNOSTICS ? "" : null;

//    @Log.Number (name = "FL")
    public EncodedMotor<DcMotorEx> fl2;
//    @Log.Number (name = "FR")
    public EncodedMotor<DcMotorEx> fr2;
//    @Log.Number (name = "RL")
    public EncodedMotor<DcMotorEx> rl2;
//    @Log.Number (name = "RR")
    public EncodedMotor<DcMotorEx> rr2;

    public DrivebaseSubsystem(EncodedMotor<DcMotorEx> fl, EncodedMotor<DcMotorEx> fr,
                              EncodedMotor<DcMotorEx> rl, EncodedMotor<DcMotorEx> rr,
                              IMU i,
                              Rev2MDistanceSensor front/*, Rev2MDistanceSensor left, Rev2MDistanceSensor right*/) {
        super(fl, fr, rl, rr, i, () -> DriveConstants.class);
        fl2 = fl; fr2 = fr; rl2 = rl; rr2 = rr;
        //this.front_distance = front;
        //this.left_distance = left;
        //this.right_distance = right;
    }

    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }

    @Override
    public void periodic() {
        if (ENABLE_POSE_DIAGNOSTICS) {
            updatePoseEstimate();
            Pose2d pose = getPoseEstimate();
            Pose2d poseVelocity = getPoseVelocity();
            poseDisplay = pose.toString() + " : " + (poseVelocity != null ? poseVelocity.toString() : "<null>");
            System.out.println("Pose: " + poseDisplay);
        }
    }
}

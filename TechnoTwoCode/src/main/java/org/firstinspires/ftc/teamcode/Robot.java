package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Robot implements Loggable {
    @Config
    public static class RobotConstants {
        public static boolean LIFT_CONNECTED = false;
        public static boolean DEPOSIT_CONNECTED = false;
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CAROUSEL_CONNECTED = true;
        public static boolean INTAKE_CONNECTED = false;
        public static boolean VISION_CONNECTED = false;
        public static boolean ARM_CONNECTED = false;
        public static boolean CAP_CONNECTED = false;
        public static boolean BUCKET_CONNECTED = false;
    }
    public DepositSubsystem depositSubsystem;
    public DrivebaseSubsystem drivebaseSubsystem;
    public CarouselSubsystem carouselSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public VisionSubsystem visionSubsystem;
    public ArmSubsystem armSubsystem;
    public BucketSubsystem bucketSubsystem;

    public Robot() {
        if (DEPOSIT_CONNECTED) depositSubsystem = new DepositSubsystem(Hardware.dumpServo);
        if (DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(Hardware.flDriveMotor, Hardware.frDriveMotor, Hardware.rlDriveMotor, Hardware.rrDriveMotor, Hardware.imu);
        if (CAROUSEL_CONNECTED) carouselSubsystem = new CarouselSubsystem(Hardware.carouselMotor);
        if (INTAKE_CONNECTED) intakeSubsystem = new IntakeSubsystem(Hardware.intakeMotor, Hardware.intakeDistSensor);
        if (VISION_CONNECTED) visionSubsystem = new VisionSubsystem(Hardware.camera);
        if (ARM_CONNECTED) armSubsystem = new ArmSubsystem(Hardware.armMotor);
        if (BUCKET_CONNECTED) bucketSubsystem = new BucketSubsystem(Hardware.bucketMotor, Hardware.bucketServo);
    }
}

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Robot implements Loggable {
    @Config
    public static class RobotConstants {
        public static boolean LIFT_CONNECTED = false;
        public static boolean DEPOSIT_CONNECTED = false;
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CAROUSEL_CONNECTED = false;
        public static boolean INTAKE_CONNECTED = false;
        public static boolean VISION_CONNECTED = false;
    }
    public LiftSubsystem liftSubsystem;
    public DepositSubsystem depositSubsystem;
    public DrivebaseSubsystem drivebaseSubsystem;
    public CarouselSubsystem carouselSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public VisionSubsystem visionSubsystem;
    public Robot() {
        if (LIFT_CONNECTED) liftSubsystem = new LiftSubsystem(Hardware.liftMotor);
        if (DEPOSIT_CONNECTED) depositSubsystem = new DepositSubsystem(Hardware.leftDepositServo, Hardware.rightDepositServo);
        if (DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(Hardware.flDriveMotor, Hardware.frDriveMotor, Hardware.rlDriveMotor, Hardware.rrDriveMotor);
        if (CAROUSEL_CONNECTED) carouselSubsystem = new CarouselSubsystem(Hardware.carouselMotor);
        if (INTAKE_CONNECTED) intakeSubsystem = new IntakeSubsystem(Hardware.intakeMotor);
        if (VISION_CONNECTED) visionSubsystem = new VisionSubsystem(Hardware.camera);
    }
}

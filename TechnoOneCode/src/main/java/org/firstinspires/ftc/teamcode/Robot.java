package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Robot implements Loggable {
    @Config
    public static class RobotConstants {
        public static boolean LIFT_CONNECTED = true;
        public static boolean DEPOSIT_CONNECTED = true;
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CAROUSEL_CONNECTED = true;
        public static boolean INTAKE_CONNECTED = false;
        public static boolean VISION_CONNECTED = false;
    }

    @Log.NumberBar(name = "Lift", min = 0, max = 1000, scale = 100)
    public LiftSubsystem liftSubsystem;

    @Log(name = "Deposit")
    public DepositSubsystem depositSubsystem;

    @Log(name = "Drivebase")
    public DrivebaseSubsystem drivebaseSubsystem;

    @Log.NumberSlider(name = "Carousel")
    public CarouselSubsystem carouselSubsystem;

    @Log.NumberSlider(name = "Intake")
    public IntakeSubsystem intakeSubsystem;

    @Log.Number(name = "Vision")
    public VisionSubsystem visionSubsystem;

    public Robot(){
        if(LIFT_CONNECTED) liftSubsystem = new LiftSubsystem(Hardware.liftMotor);

        if(DEPOSIT_CONNECTED) depositSubsystem = new DepositSubsystem(Hardware.dumpServo, Hardware.armServo);

        if(DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(Hardware.flDriveMotor, Hardware.frDriveMotor, Hardware.rlDriveMotor, Hardware.rrDriveMotor, Hardware.imu);

        if(CAROUSEL_CONNECTED) carouselSubsystem = new CarouselSubsystem(Hardware.carouselMotor);

        if(INTAKE_CONNECTED) intakeSubsystem = new IntakeSubsystem(Hardware.intakeMotor);

        if(VISION_CONNECTED) visionSubsystem = new VisionSubsystem(Hardware.camera);
    }
}

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.util.Color;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.opmodes.TeleOpMode;
import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;
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
        public static boolean INTAKE_CONNECTED = true;
        public static boolean VISION_CONNECTED = true;
        public static boolean CAP_CONNECTED = false;
    }

    @Log.NumberBar(name = "Lift", min = 0, max = 1100, scale = 100, completeBarColor = Color.PURPLE)
    public LiftSubsystem liftSubsystem;

    @Log(name = "Deposit", entryColor = Color.PINK)
    public DepositSubsystem depositSubsystem;

    @Log(name = "Drivebase", entryColor = Color.BLUE)
    public DrivebaseSubsystem drivebaseSubsystem;

    @Log.NumberSlider(name = "Carousel", sliderBackground = Color.CYAN, slider = Color.LIME)
    public CarouselSubsystem carouselSubsystem;

    @Log.NumberSlider(name = "Intake", sliderBackground = Color.RED, slider = Color.ORANGE)
    public IntakeSubsystem intakeSubsystem;

    public VisionSubsystem visionSubsystem;

    @Log.NumberSlider(name = "Cap", color = Color.MAGENTA)
    public CapSubsystem capSubsystem;

    public Robot(Hardware hardware){
        if(LIFT_CONNECTED) liftSubsystem = new LiftSubsystem(hardware.liftMotor);

        if(DEPOSIT_CONNECTED) depositSubsystem = new DepositSubsystem(hardware.dumpServos, hardware.armServo);

        if(DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor,
                hardware.imu, hardware.leftRangeSensor, hardware.rightRangeSensor, hardware.frontRangeSensor);

        if(CAROUSEL_CONNECTED) carouselSubsystem = new CarouselSubsystem(hardware.carouselMotor);

        if(INTAKE_CONNECTED) intakeSubsystem = new IntakeSubsystem(hardware.intakeMotor, hardware.intakeDistSensor);

        if(VISION_CONNECTED) visionSubsystem = new VisionSubsystem(hardware.camera);

        if(CAP_CONNECTED) capSubsystem = new CapSubsystem(hardware.capServo);
    }
}

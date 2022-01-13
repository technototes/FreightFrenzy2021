package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.util.Color;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SpeakerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.*;

public class Robot implements Loggable {
    @Config
    public static class SubsystemConstants {
        public static boolean LIFT_ENABLED = true;
        public static boolean ARM_ENABLED = true;
        public static boolean EXTENSION_ENABLED = true;
        public static boolean DRIVE_ENABLED = true;
        public static boolean CAROUSEL_ENABLED = true;
        public static boolean INTAKE_ENABLED = true;
        public static boolean VISION_ENABLED = true;
        public static boolean CAP_ENABLED = false;
        public static boolean SPEAKER_CONNECTED = true;

    }

    @Log.NumberBar(name = "Lift", min = 0, max = 500, scale = 100, completeBarColor = Color.PURPLE)
    public LiftSubsystem liftSubsystem;

    @Log(name = "Deposit", entryColor = Color.PINK)
    public ArmSubsystem armSubsystem;

    @Log(name = "Extension", entryColor = Color.BLUE)
    public ExtensionSubsystem extensionSubsystem;

    @Log(name = "Drivebase", entryColor = Color.BLUE)
    public DrivebaseSubsystem drivebaseSubsystem;

    @Log.NumberSlider(name = "Carousel", sliderBackground = Color.CYAN, slider = Color.LIME)
    public CarouselSubsystem carouselSubsystem;

    @Log(name = "Intake", entryColor = Color.RED)
    public IntakeSubsystem intakeSubsystem;

    @Log.NumberSlider(name = "Cap", color = Color.MAGENTA)
    public CapSubsystem capSubsystem;

    public VisionSubsystem visionSubsystem;

    @Log(name="Song", color = Color.WHITE, index = 0)
    public SpeakerSubsystem speakerSubsystem;

    public Robot(Hardware hardware){
        if(SPEAKER_CONNECTED) speakerSubsystem = new SpeakerSubsystem(hardware.speaker);

        if(LIFT_ENABLED) liftSubsystem = new LiftSubsystem(hardware.liftMotor);

        if(ARM_ENABLED) armSubsystem = new ArmSubsystem(hardware.dumpServo, hardware.armServo);

        if(EXTENSION_ENABLED) extensionSubsystem = new ExtensionSubsystem(hardware.slideServo, hardware.turretServo);

        if(DRIVE_ENABLED) drivebaseSubsystem = new DrivebaseSubsystem(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor,
                hardware.imu, hardware.leftRangeSensor, hardware.rightRangeSensor, hardware.frontRangeSensor);

        if(CAROUSEL_ENABLED) carouselSubsystem = new CarouselSubsystem(hardware.carouselMotor);

        if(INTAKE_ENABLED) intakeSubsystem = new IntakeSubsystem(hardware.intakeMotor, hardware.intakeSensor);

        if(VISION_ENABLED) visionSubsystem = new VisionSubsystem(hardware.camera);

        if(CAP_ENABLED) capSubsystem = new CapSubsystem(hardware.capServo);
    }
}

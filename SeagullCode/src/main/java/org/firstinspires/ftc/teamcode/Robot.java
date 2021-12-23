package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Robot implements Loggable {
    @Config
    public static class RobotConstants {
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CAROUSEL_CONNECTED = true;
        public static boolean INTAKE_CONNECTED = true;
        public static boolean VISION_CONNECTED = false;
        public static boolean DUMP_CONNECTED = true;
    }
    public DrivebaseSubsystem drivebaseSubsystem;
    public CarouselSubsystem carouselSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public VisionSubsystem visionSubsystem;
    public DumpSubsystem dumpSubsystem;

    public Robot(Hardware hardware) {
        if (DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor, hardware.imu, hardware.frontDistanceSensor, hardware.leftDistanceSensor, hardware.rightDistanceSensor);
        if (CAROUSEL_CONNECTED) carouselSubsystem = new CarouselSubsystem(hardware.carouselMotor);
        if (INTAKE_CONNECTED) intakeSubsystem = new IntakeSubsystem(hardware.intakeMotor, hardware.bucketDistanceSensor);
        if (VISION_CONNECTED) visionSubsystem = new VisionSubsystem(hardware.camera);
        if (DUMP_CONNECTED) dumpSubsystem = new DumpSubsystem(hardware.bucketMotor, hardware.bucketServo);
    }
}

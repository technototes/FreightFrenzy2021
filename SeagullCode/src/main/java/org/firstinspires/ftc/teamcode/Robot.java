package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Robot implements Loggable {
    @Config
    public static class RobotConstants {
        public static boolean DRIVE_CONNECTED = true;
    }
    public DrivebaseSubsystem drivebaseSubsystem;

    public Robot(Hardware hardware) {
        if (DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor, hardware.imu, hardware.frontDistanceSensor);// , hardware.leftDistanceSensor, hardware.rightDistanceSensor
    }
}

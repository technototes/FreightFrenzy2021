package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Log;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
@Disabled
@TeleOp(name="drive test")
public class DriveTest extends CommandOpMode {
    @Log(name="drive pose")
    public DrivebaseSubsystem drivebaseSubsystem;
    public Hardware hardware;

    @Override
    public void uponInit() {
        RobotConstants.updateAlliance(Alliance.RED);
        hardware = new Hardware();
        drivebaseSubsystem = new DrivebaseSubsystem(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor,
                hardware.imu, hardware.leftRangeSensor, hardware.rightRangeSensor, hardware.frontRangeSensor);
        drivebaseSubsystem.setDefaultCommand(new DriveCommand(drivebaseSubsystem, driverGamepad.leftStick, driverGamepad.rightStick));
        driverGamepad.rightStickButton.whenPressed(new DriveResetCommand(drivebaseSubsystem));

    }

    @Override
    public void runLoop() {
        drivebaseSubsystem.update();
    }
}

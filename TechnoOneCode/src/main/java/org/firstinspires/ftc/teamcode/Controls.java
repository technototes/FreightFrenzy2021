package org.firstinspires.ftc.teamcode;

import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmDumpCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;

public class Controls {
    public CommandGamepad gamepad;

    public Robot robot;

    public CommandButton dumpButton;
    public CommandButton extendButton;

    public CommandButton resetGyroButton;

    public Controls(CommandGamepad g, Robot r){
        gamepad = g;
        robot = r;

        dumpButton = gamepad.a;
        extendButton = gamepad.x;
        resetGyroButton = gamepad.rightStickButton;

        if(Robot.LIFT_CONNECTED) bindLiftControls();
        if(Robot.DEPOSIT_CONNECTED) bindDepositControls();
        if(Robot.DRIVE_CONNECTED) bindDriveControls();
    }

    public void bindDepositControls(){
        dumpButton.whenPressed(new ArmDumpCommand(robot.depositSubsystem))
            .whenReleased(new ArmRetractCommand(robot.depositSubsystem));
        extendButton.whenPressed(new ArmExtendCommand(robot.depositSubsystem));
    }

    public void bindLiftControls(){
    }

    public void bindDriveControls(){
        resetGyroButton.whileReleased(new DriveCommand(robot.drivebaseSubsystem, gamepad.leftStick, gamepad.rightStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }
}

package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;

public class Controls {
    public CommandGamepad gamepad;

    public Robot robot;

    public CommandButton topDepositButton, middleDepositButton, sharedDepositButton;

    public CommandButton intakeInButton, intakeOutButton;
    public CommandAxis intakeInTrigger, carryDepositButton;

    public CommandButton carouselButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;

    public Alliance alliance;

    public Controls(CommandGamepad g, Robot r, Alliance alliance) {
        gamepad = g;
        robot = r;
        this.alliance = alliance;

        sharedDepositButton = gamepad.leftBumper;
        middleDepositButton = gamepad.dpadRight;
        topDepositButton = gamepad.rightBumper;
        carryDepositButton = gamepad.leftTrigger;

        intakeInTrigger = gamepad.rightTrigger;

        intakeInButton = gamepad.cross;
        intakeOutButton = gamepad.circle;

        carouselButton = gamepad.triangle;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.circle;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.square;



        if (DRIVE_CONNECTED) bindDriveControls();
    }

    public void bindDriveControls() {
        // robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        CommandScheduler.getInstance().scheduleJoystick(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }
}

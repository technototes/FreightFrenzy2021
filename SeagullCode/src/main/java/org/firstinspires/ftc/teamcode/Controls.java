package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.teamcode.commands.drivebase.DebugDriveToCenterCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveToCenterCommandGroup;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;

public class Controls {
    public CommandGamepad gamepad;

    public Robot robot;

    public CommandButton topDepositButton, middleDepositButton, sharedDepositButton;

    public CommandButton intakeInButton, intakeOutButton;
    public CommandAxis intakeInTrigger, carryDepositButton;

    public CommandButton squareButton, triangleButton, circleButton, xButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;

    public Controls(CommandGamepad g, Robot r, boolean enableExperimentalFeatures) {
        gamepad = g;
        robot = r;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.circle;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.square;

        squareButton = gamepad.square;
        triangleButton = gamepad.triangle;

        if (DRIVE_CONNECTED) bindDriveControls();
        if (enableExperimentalFeatures) bindExperimentalControls();
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance().scheduleJoystick(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindExperimentalControls() {
        squareButton.whenPressed(new DriveToCenterCommandGroup(robot.drivebaseSubsystem, robot.drivebaseSubsystem.getPoseEstimate()));
        triangleButton.whenPressed(new DebugDriveToCenterCommand(robot.drivebaseSubsystem, robot.drivebaseSubsystem.getPoseEstimate()));
    }
}

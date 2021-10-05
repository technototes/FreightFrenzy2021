package org.firstinspires.ftc.samplecode.finch;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;

public class OperatorInterface {
    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton armLowerButton, armRaiseButton;
    public OperatorInterface(CommandGamepad g, Robot r){
        robot = r;
        gamepad = g;

        armLowerButton = gamepad.leftBumper;
        armRaiseButton = gamepad.rightBumper;

        armLowerButton.whenPressed(robot.armSubsystem::lower);

        armRaiseButton.whenPressed(robot.armSubsystem::raise);
        armRaiseButton.whenReleased(robot.armSubsystem::carry);

        gamepad.x.whilePressed(robot.drivebaseSubsystem::forward);

        CommandScheduler.getInstance().schedule(()->robot.drivebaseSubsystem
                .tankDrive(g.leftStickX.getAsDouble(), g.rightStickY.getAsDouble()));
    }
}

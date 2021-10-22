package org.firstinspires.ftc.teamcode;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;
import com.technototes.library.control.gamepad.Stick;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftDownCommand;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Controls {

    public CommandGamepad gamepad;

    public Robot robot;

    public CommandAxis dumpAxis, toIntakeButton;
    public CommandButton neutralHubButton, specificHubButton;

    public CommandButton liftAdjustUpButton, liftAdjustDownButton, slideAdjustInButton, slideAdjustOutButton;

    public CommandButton intakeInButton, intakeOutButton;

    public CommandButton carouselLeftButton, carouselRightButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, snailSpeedButton;

    public Controls(CommandGamepad g, Robot r){
        gamepad = g;
        robot = r;

        dumpAxis = gamepad.leftTrigger;
        neutralHubButton = gamepad.leftBumper;
        specificHubButton = gamepad.rightBumper;
        toIntakeButton = gamepad.rightTrigger.setTriggerThreshold(0.3);

        liftAdjustUpButton = gamepad.dpadUp;
        liftAdjustDownButton = gamepad.dpadDown;
        slideAdjustInButton = gamepad.dpadRight;
        slideAdjustOutButton = gamepad.dpadLeft;

        intakeInButton = gamepad.a;
        intakeOutButton = gamepad.b;

        carouselLeftButton = gamepad.x;
        carouselRightButton = gamepad.y;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.leftStickButton;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        if(LIFT_CONNECTED) bindLiftControls();
        if(DEPOSIT_CONNECTED) bindDepositControls();
        if(DRIVE_CONNECTED) bindDriveControls();
        if(INTAKE_CONNECTED) bindIntakeControls();
        if(CAROUSEL_CONNECTED) bindCarouselControls();
    }

    public void bindDepositControls(){
        dumpAxis.whilePressed(new DumpVariableCommand(robot.depositSubsystem, dumpAxis));
        toIntakeButton.whenPressed(new ArmRetractCommand(robot.depositSubsystem));
        specificHubButton.whenPressed(new WaitCommand(1).andThen(new ArmExtendCommand(robot.depositSubsystem)));
        neutralHubButton.whenPressed(new WaitCommand(1).andThen(new ArmExtendCommand(robot.depositSubsystem)));
        slideAdjustOutButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, -0.05));
        slideAdjustInButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, 0.05));
    }

    public void bindLiftControls(){
        neutralHubButton.whenPressed(new LiftCommand(robot.liftSubsystem, 1000));
        specificHubButton.whenPressed(new LiftCommand(robot.liftSubsystem, 6000));
        toIntakeButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));

    }

    public void bindDriveControls(){
        robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls(){

    }

    public void bindCarouselControls(){

    }
}

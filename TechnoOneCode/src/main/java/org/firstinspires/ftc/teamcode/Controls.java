package org.firstinspires.ftc.teamcode;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;
import com.technototes.library.control.gamepad.Stick;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel1Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftTranslateCommand;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DEPOSIT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.LIFT_CONNECTED;

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

    public Controls(CommandGamepad g, Robot r) {
        gamepad = g;
        robot = r;

        dumpAxis = gamepad.leftTrigger.setTriggerThreshold(0.3);
        neutralHubButton = gamepad.leftBumper;
        specificHubButton = gamepad.rightBumper;
        toIntakeButton = gamepad.rightTrigger.setTriggerThreshold(0.3);

        liftAdjustUpButton = gamepad.dpadUp;
        liftAdjustDownButton = gamepad.dpadDown;
        slideAdjustInButton = gamepad.dpadRight;
        slideAdjustOutButton = gamepad.dpadLeft;

        intakeInButton = gamepad.cross;
        intakeOutButton = gamepad.circle;

        carouselLeftButton = gamepad.square;
        carouselRightButton = gamepad.triangle;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.leftStickButton;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        if (LIFT_CONNECTED) bindLiftControls();
        if (DEPOSIT_CONNECTED) bindDepositControls();
        if (DRIVE_CONNECTED) bindDriveControls();
        if (INTAKE_CONNECTED) bindIntakeControls();
        if (CAROUSEL_CONNECTED) bindCarouselControls();
        if (CAP_CONNECTED) bindCapControls();
    }




    public void bindDepositControls() {
        dumpAxis.whilePressedOnce(new DumpVariableCommand(robot.depositSubsystem, dumpAxis));
        toIntakeButton.whilePressedContinuous(new ArmRetractCommand(robot.depositSubsystem));
        specificHubButton.whenPressed(new ArmExtendCommand(robot.depositSubsystem));
        neutralHubButton.whenPressed(new ArmExtendCommand(robot.depositSubsystem));
        slideAdjustOutButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, -0.01));
        slideAdjustInButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, 0.01));
    }

    public void bindLiftControls() {
        neutralHubButton.whenPressed(new LiftLevel1Command(robot.liftSubsystem).withTimeout(1.5));
        specificHubButton.whenPressed(new LiftLevel3Command(robot.liftSubsystem).withTimeout(1.5));
        toIntakeButton.whenPressed(new LiftCollectCommand(robot.liftSubsystem).withTimeout(1.5));
        liftAdjustUpButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, 10));
        liftAdjustDownButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, -10));
    }

    public void bindDriveControls() {
        robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        specificHubButton.whenPressed(new SetSpeedCommand(robot.drivebaseSubsystem).cancelUpon(toIntakeButton));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls() {
        toIntakeButton.whilePressedContinuous(DEPOSIT_CONNECTED ? new WaitCommand(1).andThen(new IntakeSafeCommand(robot.intakeSubsystem, robot.depositSubsystem)) : new IntakeInCommand(robot.intakeSubsystem));
        intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));
        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
        specificHubButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));
        neutralHubButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));

    }

    public void bindCarouselControls() {
        carouselLeftButton.whilePressedOnce(new CarouselLeftCommand(robot.carouselSubsystem));
        carouselRightButton.whilePressedOnce(new CarouselRightCommand(robot.carouselSubsystem));
    }

    public void bindCapControls() {

    }


}

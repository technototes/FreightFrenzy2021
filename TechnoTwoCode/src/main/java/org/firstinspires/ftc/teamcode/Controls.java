package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DUMP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DEPOSIT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;
import com.technototes.library.control.gamepad.Stick;

import org.firstinspires.ftc.teamcode.commands.bucket.BucketCarryCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketCollectCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadBottomLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadBottomLevelCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;

public class Controls {

    public CommandGamepad gamepad;

    public Robot robot;

    public CommandAxis toIntakeButton;
    public CommandButton carryButton, collectButton, topDepositButton,
            middleDepositButton, bottomDepositButton;

    public CommandButton liftAdjustUpButton, liftAdjustDownButton, slideAdjustInButton, slideAdjustOutButton;

    public CommandButton intakeInButton, intakeOutButton;

    public CommandButton carouselLeftButton, carouselRightButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, snailSpeedButton;

    public Controls(CommandGamepad g, Robot r){
        gamepad = g;
        robot = r;

        collectButton = gamepad.leftBumper;
        carryButton = gamepad.rightBumper;
        bottomDepositButton = gamepad.dpadDown;
        middleDepositButton = gamepad.dpadRight;
        topDepositButton = gamepad.dpadUp;

        intakeInButton = gamepad.cross;
        intakeOutButton = gamepad.circle;

        carouselLeftButton = gamepad.square;
        carouselRightButton = gamepad.triangle;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.leftStickButton;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        if(DRIVE_CONNECTED) bindDriveControls();
        if(INTAKE_CONNECTED) bindIntakeControls();
        if(CAROUSEL_CONNECTED) bindCarouselControls();
        if(CAP_CONNECTED) bindCapControls();
        if(DUMP_CONNECTED) bindBucketControls();
    }

    public void bindBucketControls(){
        carryButton.whilePressedOnce(new DumpCarryCommand(robot.dumpSubsystem));
        collectButton.whenPressed(new DumpCollectCommand(robot.dumpSubsystem));
        topDepositButton.whenPressed(new DumpUnloadTopLevelCommand(robot.dumpSubsystem));
        bottomDepositButton.whenPressed(new DumpUnloadBottomLevelCommand(robot.dumpSubsystem));

    }

    public void bindDriveControls(){
        robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls(){
        if(DEPOSIT_CONNECTED) {
            toIntakeButton.whenPressed(new WaitCommand(1).andThen(new IntakeSafeCommand(robot.intakeSubsystem, robot.depositSubsystem)));
            intakeInButton.whilePressedContinuous(new IntakeSafeCommand(robot.intakeSubsystem, robot.depositSubsystem));
        }else{
            toIntakeButton.whenPressed(new IntakeInCommand(robot.intakeSubsystem));
            intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));

        }

        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
    }

    public void bindCarouselControls(){
        carouselLeftButton.whilePressedOnce(new CarouselLeftCommand(robot.carouselSubsystem));
        carouselRightButton.whilePressedOnce(new CarouselRightCommand(robot.carouselSubsystem));
    }

    public void bindCapControls() {

    }
}

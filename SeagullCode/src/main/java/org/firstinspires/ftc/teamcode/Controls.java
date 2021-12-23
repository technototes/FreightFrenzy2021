package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DUMP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;

import com.acmerobotics.roadrunner.drive.Drive;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.StraightenCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadBottomLevelCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadSharedHubCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;

public class Controls {
    public CommandGamepad gamepad;

    public Robot robot;

    public CommandButton carryButton, collectButton, topDepositButton,
              middleDepositButton, bottomDepositButton, sharedDepositButton;

    public CommandButton intakeInButton, intakeOutButton;
    public CommandAxis intakeInTrigger, intakeOutTrigger;

    public CommandButton carouselLeftButton, carouselRightButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, straightenButton, snailSpeedButton;

    public Controls(CommandGamepad g, Robot r) {
        gamepad = g;
        robot = r;

        collectButton = gamepad.leftBumper;
        carryButton = gamepad.rightBumper;
        sharedDepositButton = gamepad.dpadLeft;
        bottomDepositButton = gamepad.dpadDown;
        middleDepositButton = gamepad.dpadRight;
        topDepositButton = gamepad.dpadUp;

        intakeInTrigger = gamepad.rightTrigger;
        intakeOutTrigger = gamepad.leftTrigger;

        intakeInButton = gamepad.cross;
        intakeOutButton = gamepad.circle;

        carouselLeftButton = gamepad.square; // slow
        carouselRightButton = gamepad.triangle; // fast

        resetGyroButton = gamepad.rightStickButton;
        straightenButton = gamepad.options;
        snailSpeedButton = gamepad.leftStickButton;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;



        if (DRIVE_CONNECTED) bindDriveControls();
        if (INTAKE_CONNECTED) bindIntakeControls();
        if (CAROUSEL_CONNECTED) bindCarouselControls();
        if (DUMP_CONNECTED) bindBucketControls();
    }

    public void bindBucketControls() {
        carryButton.whenPressed(new DumpCarryCommand(robot.dumpSubsystem));
        collectButton.whenPressed(new DumpCollectCommand(robot.dumpSubsystem));
        topDepositButton.whenPressed(new DumpUnloadTopLevelCommand(robot.dumpSubsystem));
        middleDepositButton.whenPressed(new DumpUnloadMiddleLevelCommand(robot.dumpSubsystem));
        bottomDepositButton.whenPressed(new DumpUnloadBottomLevelCommand(robot.dumpSubsystem));
        sharedDepositButton.whenPressed(new DumpUnloadSharedHubCommand(robot.dumpSubsystem));
    }

    public void bindDriveControls() {
        // TODO: Fix DefaultCommands so they don't interfere with other drivebase commands
        // robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        CommandScheduler.getInstance().scheduleJoystick(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        straightenButton.whenPressed(new StraightenCommand((robot.drivebaseSubsystem)));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls() {
        intakeInButton.whenPressed(new IntakeSafeCommand(robot.intakeSubsystem, robot.dumpSubsystem, gamepad));
        intakeOutButton.whenToggled(new IntakeOutCommand(robot.intakeSubsystem));
        intakeOutButton.whenInverseToggled(new IntakeStopCommand(robot.intakeSubsystem));

        intakeInTrigger.whenPressed(new IntakeSafeCommand(robot.intakeSubsystem, robot.dumpSubsystem, gamepad));
        intakeOutTrigger.whenToggled(new IntakeOutCommand(robot.intakeSubsystem));
        intakeOutTrigger.whenInverseToggled(new IntakeStopCommand(robot.intakeSubsystem));
    }

    public void bindCarouselControls() {
        carouselLeftButton.whilePressedOnce(new CarouselLeftCommand(robot.carouselSubsystem));
        carouselRightButton.whilePressedOnce(new CarouselRightCommand(robot.carouselSubsystem));
    }
}

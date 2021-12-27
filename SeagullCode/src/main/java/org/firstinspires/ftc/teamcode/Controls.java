package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DUMP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Alliance;


import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselBlueFastCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselBlueSlowCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRedFastCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRedSlowCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
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

    public CommandButton carouselSlowButton, carouselFastButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;

    public Alliance alliance;

    public Controls(CommandGamepad g, Robot r, Alliance alliance) {
        gamepad = g;
        robot = r;
        this.alliance = alliance;

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

        carouselSlowButton = gamepad.square;
        carouselFastButton = gamepad.triangle;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.leftStickButton;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.options;



        if (DRIVE_CONNECTED) bindDriveControls();
        if (INTAKE_CONNECTED) bindIntakeControls();
        if (CAROUSEL_CONNECTED) {
            if (alliance == Alliance.RED) {
                bindCarouselRedControls();
            }
            else {
                bindCarouselBlueControls();
            }
        }
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
        CommandScheduler.getInstance().scheduleJoystick(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
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

    public void bindCarouselRedControls() {
        carouselSlowButton.whilePressedOnce(new CarouselRedSlowCommand(robot.carouselSubsystem));
        carouselFastButton.whilePressedOnce(new CarouselRedFastCommand(robot.carouselSubsystem));
    }
    public void bindCarouselBlueControls() {
        carouselSlowButton.whilePressedOnce(new CarouselBlueSlowCommand(robot.carouselSubsystem));
        carouselFastButton.whilePressedOnce(new CarouselBlueFastCommand(robot.carouselSubsystem));
    }
}

package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DUMP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselBlueFastCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselBlueSlowCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRedFastCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRedSlowCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadSharedHubCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;

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
        intakeInTrigger.whenPressed(
                new SequentialCommandGroup(true,
                new DumpCollectCommand(robot.dumpSubsystem),
                new IntakeSafeCommand(robot.intakeSubsystem, robot.dumpSubsystem, gamepad))
        );
        topDepositButton.whenPressed(new DumpUnloadTopLevelCommand(robot.dumpSubsystem).alongWith(new IntakeStopCommand(robot.intakeSubsystem)));
        sharedDepositButton.whenPressed(new DumpUnloadSharedHubCommand(robot.dumpSubsystem).alongWith(new IntakeStopCommand(robot.intakeSubsystem)));
        carryDepositButton.whenPressed(new DumpCarryCommand(robot.dumpSubsystem).alongWith(new IntakeStopCommand(robot.intakeSubsystem)));
    }

    public void bindDriveControls() {
        // robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        CommandScheduler.getInstance().scheduleJoystick(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls() {
        intakeInButton.whenPressed(new IntakeSafeCommand(robot.intakeSubsystem, robot.dumpSubsystem, gamepad));
        intakeOutButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem));
        intakeOutButton.whenReleased(new IntakeStopCommand(robot.intakeSubsystem));
    }

    public void bindCarouselRedControls() {
//        carouselButton.whilePressedOnce(new CarouselRedSlowCommand(robot.carouselSubsystem));
        carouselButton.whilePressed(new CarouselRedSlowCommand(robot.carouselSubsystem));
        carouselButton.whenReleased(new CarouselRedFastCommand(robot.carouselSubsystem).withTimeout(0.5));
    }
    public void bindCarouselBlueControls() {
//        carouselButton.whilePressedOnce(new CarouselBlueSlowCommand(robot.carouselSubsystem));
        carouselButton.whilePressed(new CarouselBlueSlowCommand(robot.carouselSubsystem));
        carouselButton.whenReleased(new CarouselBlueFastCommand(robot.carouselSubsystem).withTimeout(0.5));
    }
}

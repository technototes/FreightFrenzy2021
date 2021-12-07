package org.firstinspires.ftc.teamcode;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;
import com.technototes.library.control.gamepad.Stick;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.cap.CapDownCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.BucketDumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveResetCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel1Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftNeutralCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftTranslateCommand;

import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DEPOSIT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.EXTENSION_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.INTAKE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.LIFT_CONNECTED;

public class SingleDriverControls {

    public CommandGamepad driverGamepad;

    public Robot robot;

    public CommandAxis dumpAxis, toIntakeButton;
    public CommandButton sharedHubButton, allianceHubButton;

    public CommandButton liftAdjustUpButton, liftAdjustDownButton, slideAdjustInButton, slideAdjustOutButton;

    public CommandButton intakeInButton, intakeOutButton;

    public CommandButton carouselButton, carouselBackButton;

    public CommandButton capUpButton, capDownButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, snailSpeedButton;


    public SingleDriverControls(Robot r, CommandGamepad driver, CommandGamepad codriver) {
        driverGamepad = driver;
        robot = r;

        dumpAxis = driverGamepad.leftTrigger.setTriggerThreshold(0.2);
        sharedHubButton = driverGamepad.leftBumper;
        allianceHubButton = driverGamepad.rightBumper;
        toIntakeButton = driverGamepad.rightTrigger.setTriggerThreshold(0.3);

        liftAdjustUpButton = driverGamepad.dpadUp;
        liftAdjustDownButton = driverGamepad.dpadDown;
        slideAdjustInButton = driverGamepad.dpadRight;
        slideAdjustOutButton = driverGamepad.dpadLeft;

        intakeInButton = driverGamepad.cross;
        intakeOutButton = driverGamepad.circle;

        carouselButton = driverGamepad.square;
        carouselBackButton = driverGamepad.triangle;

        resetGyroButton = driverGamepad.rightStickButton;
        snailSpeedButton = driverGamepad.leftStickButton;

        driveLeftStick = driverGamepad.leftStick;
        driveRightStick = driverGamepad.rightStick;

        capUpButton = driverGamepad.start;
        capDownButton = driverGamepad.back;

        if (LIFT_CONNECTED) bindLiftControls();
        if (DEPOSIT_CONNECTED) bindDepositControls();
        if (DRIVE_CONNECTED) bindDriveControls();
        if (INTAKE_CONNECTED) bindIntakeControls();
        if (CAROUSEL_CONNECTED) bindCarouselControls();
        if (CAP_CONNECTED) bindCapControls();
        if (EXTENSION_CONNECTED) bindExtensionControls();
    }


    public void bindDepositControls() {
        dumpAxis.whilePressedOnce(new BucketDumpVariableCommand(robot.depositSubsystem, dumpAxis).asConditional(EXTENSION_CONNECTED ? robot.extensionSubsystem::isSlideOut : ()->true));
        toIntakeButton.whenPressed(new ArmRaiseCommand(robot.depositSubsystem).sleep(0.3).andThen(new ArmInCommand(robot.depositSubsystem)));
        allianceHubButton.whenPressed(new ArmOutCommand(robot.depositSubsystem));
        sharedHubButton.whenPressed(new ArmOutCommand(robot.depositSubsystem));
        slideAdjustOutButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, -0.03));
        slideAdjustInButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, 0.03));
    }

    public void bindLiftControls() {
        sharedHubButton.whenPressed(new WaitCommand(0.3).andThen(new LiftNeutralCommand(robot.liftSubsystem).withTimeout(1.5)));
        allianceHubButton.whenPressed(new WaitCommand(0.3).andThen(new LiftLevel3Command(robot.liftSubsystem).withTimeout(1.5)));
        toIntakeButton.whenPressed(new WaitCommand(0.8).deadline(new LiftLevel1Command(robot.liftSubsystem)).andThen(new LiftCollectCommand(robot.liftSubsystem).withTimeout(1.5)));
        liftAdjustUpButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, 50));
        liftAdjustDownButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, -50));
    }

    public void bindDriveControls() {
        robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        allianceHubButton.whenPressed(new DriveSpeedCommand(robot.drivebaseSubsystem).cancelUpon(toIntakeButton));
        resetGyroButton.whenPressed(new DriveResetCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new DriveSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls() {
        toIntakeButton.whenPressed(new WaitCommand(1).andThen(new IntakeInCommand(robot.intakeSubsystem)));
        intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));
        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
        allianceHubButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));
        sharedHubButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));

    }

    public void bindCarouselControls() {
        carouselButton.whilePressedOnce(Alliance.Selector.selectOf(RobotConstants.getAlliance(),
                new CarouselLeftCommand(robot.carouselSubsystem),
                new CarouselRightCommand(robot.carouselSubsystem)));
        carouselBackButton.whilePressedOnce(Alliance.Selector.selectOf(RobotConstants.getAlliance(),
                new CarouselRightCommand(robot.carouselSubsystem),
                new CarouselLeftCommand(robot.carouselSubsystem)));
    }

    public void bindCapControls() {
        capDownButton.whenPressed((LIFT_CONNECTED ? new CapDownCommand(robot.capSubsystem, robot.liftSubsystem) : new CapDownCommand(robot.capSubsystem)).cancelUpon(capUpButton));
    }

    public void bindExtensionControls() {
        allianceHubButton.whenPressed(new ExtensionOutCommand(robot.extensionSubsystem));
        sharedHubButton.whenPressed(Alliance.Selector.selectOf(RobotConstants.getAlliance(),
                new ExtensionRightSideCommand(robot.extensionSubsystem),
                new ExtensionLeftSideCommand(robot.extensionSubsystem)));
        toIntakeButton.whenPressed(new ExtensionCollectCommand(robot.extensionSubsystem));
    }


}

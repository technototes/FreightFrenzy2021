package org.firstinspires.ftc.teamcode;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.CommandInput;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmRaiseInCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmSharedCommand;
import org.firstinspires.ftc.teamcode.commands.cap.CapDownCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.arm.BucketDumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveResetCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.TurretTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel1Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevelCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftTranslateCommand;

import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAP_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DEPOSIT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.EXTENSION_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.INTAKE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.LIFT_CONNECTED;

public class BaseControls {

    public CommandGamepad driverGamepad, codriverGamepad;

    public Robot robot;

    public CommandAxis dumpAxis;
    public CommandInput<?> toIntakeButton;
    public CommandButton sharedHubButton, allianceHubButton;

    public CommandButton liftAdjustUpButton, liftAdjustDownButton, turretAdjustRightButton, turretAdjustLeftButton;

    public CommandButton intakeInButton, intakeOutButton;

    public CommandButton carouselButton, carouselBackButton;

    public CommandButton capUpButton, capDownButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, snailSpeedButton;

    public BaseControls(Robot r, CommandGamepad driver, CommandGamepad codriver) {
        this(r, driver, codriver, true);
    }

    public BaseControls(Robot r, CommandGamepad driver, CommandGamepad codriver, boolean bind) {
        driverGamepad = driver;
        codriverGamepad = codriver;
        robot = r;

        dumpAxis = driverGamepad.leftTrigger.setTriggerThreshold(0.2);
        sharedHubButton = driverGamepad.leftBumper;
        allianceHubButton = driverGamepad.rightBumper;
        toIntakeButton = driverGamepad.rightTrigger.setTriggerThreshold(0.3);

        liftAdjustUpButton = driverGamepad.dpadUp;
        liftAdjustDownButton = driverGamepad.dpadDown;
        turretAdjustRightButton = driverGamepad.dpadRight;
        turretAdjustLeftButton = driverGamepad.dpadLeft;

        intakeInButton = driverGamepad.cross;
        intakeOutButton = driverGamepad.circle;

        carouselButton = driverGamepad.square;
        carouselBackButton = driverGamepad.triangle;

        resetGyroButton = driverGamepad.rightStickButton;
        snailSpeedButton = driverGamepad.leftStickButton;

        driveLeftStick = driverGamepad.leftStick;
        driveRightStick = driverGamepad.rightStick;

        capUpButton = driverGamepad.start;

        if(bind) bindControls();


    }

    public void bindControls(){
        if (LIFT_CONNECTED) bindLiftControls();
        if (DEPOSIT_CONNECTED) bindArmControls();
        if (DRIVE_CONNECTED) bindDriveControls();
        if (INTAKE_CONNECTED) bindIntakeControls();
        if (CAROUSEL_CONNECTED) bindCarouselControls();
        if (CAP_CONNECTED) bindCapControls();
        if (EXTENSION_CONNECTED) bindExtensionControls();
    }

    public void bindArmControls() {
        dumpAxis.whilePressedOnce(new BucketDumpVariableCommand(robot.armSubsystem, dumpAxis).asConditional(EXTENSION_CONNECTED ? robot.extensionSubsystem::isSlideOut : ()->true));
        toIntakeButton.whenPressed(new ArmRaiseInCommand(robot.armSubsystem).andThen(new ArmInCommand(robot.armSubsystem)).withTimeout(1.5));
        allianceHubButton.whenPressed(new ArmAllianceCommand(robot.armSubsystem));
        sharedHubButton.whenPressed(new ArmSharedCommand(robot.armSubsystem));

    }

    public void bindLiftControls() {
        sharedHubButton.whenPressed(new WaitCommand(0.3).andThen(new LiftSharedCommand(robot.liftSubsystem).withTimeout(0.5)));
        allianceHubButton.whenPressed(new WaitCommand(0.3).andThen(new LiftLevelCommand(robot.liftSubsystem).withTimeout(1)));
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
        toIntakeButton.whenPressed(new WaitCommand(1.3).andThen(new IntakeInCommand(robot.intakeSubsystem)));
        intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));
        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
        allianceHubButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));
        sharedHubButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));

    }

    public void bindCarouselControls() {
        carouselButton.whilePressedOnce(RobotConstants.getAlliance().selectOf(
                new CarouselLeftCommand(robot.carouselSubsystem),
                new CarouselRightCommand(robot.carouselSubsystem)));
        carouselBackButton.whilePressedOnce(RobotConstants.getAlliance().selectOf(
                new CarouselRightCommand(robot.carouselSubsystem),
                new CarouselLeftCommand(robot.carouselSubsystem)));
    }

    public void bindCapControls() {
        capDownButton.whenPressed((LIFT_CONNECTED ? new CapDownCommand(robot.capSubsystem, robot.liftSubsystem) : new CapDownCommand(robot.capSubsystem)).cancelUpon(capUpButton));
    }

    public void bindExtensionControls() {
        allianceHubButton.whenPressed(new ExtensionOutCommand(robot.extensionSubsystem));
        sharedHubButton.whenPressed(new ExtensionSideCommand(robot.extensionSubsystem));
        toIntakeButton.whenPressed(new ExtensionCollectCommand(robot.extensionSubsystem));
        turretAdjustLeftButton.whilePressed(new TurretTranslateCommand(robot.extensionSubsystem, -0.05));
        turretAdjustRightButton.whilePressed(new TurretTranslateCommand(robot.extensionSubsystem,   0.05));
    }


}

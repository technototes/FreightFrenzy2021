package org.firstinspires.ftc.teamcode;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.CommandInput;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.arm.ArmRaiseInCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmSharedCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TeleopDepositAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TeleopDepositSharedCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TeleopIntakeAllianceWarehouseCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TeleopIntakeSharedWarehouseCommand;
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
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.TurretTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel1Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevelCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftTranslateCommand;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAP_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAROUSEL_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DEPOSIT_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DRIVE_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.EXTENSION_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.INTAKE_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.LIFT_ENABLED;

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

    public CommandButton strategy1Button, strategy2Button;

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

        strategy1Button = driverGamepad.start;
        strategy2Button = driverGamepad.back;

        RobotState.setStrategy(RobotState.AllianceHubStrategy.HIGH, RobotState.SharedHubStrategy.OWN);



        if(bind) bindControls();


    }

    public void bindControls(){
        if (LIFT_ENABLED) bindLiftControls();
        if (DEPOSIT_ENABLED) bindArmControls();
        if (DRIVE_ENABLED) bindDriveControls();
        if (INTAKE_ENABLED) bindIntakeControls();
        if (CAROUSEL_ENABLED) bindCarouselControls();
        if (CAP_ENABLED) bindCapControls();
        if (EXTENSION_ENABLED) bindExtensionControls();

        strategy1Button.whenPressed(RobotState::strategy1);
        strategy2Button.whenPressed(RobotState::strategy2);
    }

    public void bindArmControls() {
        dumpAxis.whilePressedOnce(new BucketDumpVariableCommand(robot.armSubsystem, dumpAxis).asConditional(EXTENSION_ENABLED ? robot.extensionSubsystem::isSlideOut : ()->true));
        toIntakeButton.whenPressed(new WaitCommand(0).andThen(new ArmRaiseInCommand(robot.armSubsystem).andThen(new ArmInCommand(robot.armSubsystem)).withTimeout(1.5)));
        allianceHubButton.whileReleasedOnce( new ArmAllianceCommand(robot.armSubsystem).asConditional(RobotState::isDepositing));
        sharedHubButton.whileReleasedOnce(new ArmSharedCommand(robot.armSubsystem).asConditional(RobotState::isDepositing));

    }

    public void bindLiftControls() {
        sharedHubButton.whileReleasedOnce(new WaitCommand(0.3).andThen(new LiftSharedCommand(robot.liftSubsystem).withTimeout(0.5)).asConditional(RobotState::isDepositing));
        allianceHubButton.whileReleasedOnce(new WaitCommand(0.3).andThen(new LiftLevelCommand(robot.liftSubsystem).withTimeout(0.5)).asConditional(RobotState::isDepositing));
        toIntakeButton.whenPressed(new LiftLevel1Command(robot.liftSubsystem).withTimeout(0.8).andThen(new LiftCollectCommand(robot.liftSubsystem).withTimeout(0.4)));
        liftAdjustUpButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, 50));
        liftAdjustDownButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, -50));
    }

    public void bindDriveControls() {
        if(EXTENSION_ENABLED && DEPOSIT_ENABLED && LIFT_ENABLED && INTAKE_ENABLED){
            allianceHubButton.whilePressed( new TeleopDepositAllianceCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem).andThen(new TeleopIntakeAllianceWarehouseCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem)));
            sharedHubButton.whilePressed(new TeleopDepositSharedCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem).andThen(new TeleopIntakeSharedWarehouseCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem)));
        }
        robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        robot.drivebaseSubsystem.setExternalHeading(Math.toRadians(180));
//        allianceHubButton.whenPressed(new DriveSpeedCommand(robot.drivebaseSubsystem).cancelUpon(toIntakeButton));
        resetGyroButton.whenPressed(new DriveResetCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new DriveSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls() {
        toIntakeButton.whenPressed(new WaitCommand(1.5).andThen(new IntakeInCommand(robot.intakeSubsystem)));
        intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));
        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
        allianceHubButton.whenReleased(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));
        sharedHubButton.whenReleased(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));

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
        capDownButton.whenPressed((LIFT_ENABLED ? new CapDownCommand(robot.capSubsystem, robot.liftSubsystem) : new CapDownCommand(robot.capSubsystem)).cancelUpon(capUpButton));
    }

    public void bindExtensionControls() {
        allianceHubButton.whileReleasedOnce(new ExtensionCommand(robot.extensionSubsystem, ExtensionSubsystem.ExtensionConstants.TELEOP_ALLIANCE, ExtensionSubsystem.ExtensionConstants.CENTER).asConditional(RobotState::isDepositing));
        sharedHubButton.whileReleasedOnce(new ExtensionSideCommand(robot.extensionSubsystem).asConditional(RobotState::isDepositing));
        toIntakeButton.whenPressed(new ExtensionCollectCommand(robot.extensionSubsystem));
        turretAdjustLeftButton.whilePressed(new TurretTranslateCommand(robot.extensionSubsystem, 0.05, ()-> DRIVE_ENABLED && (RobotConstants.getAlliance()== Alliance.RED ^ (robot.drivebaseSubsystem.getExternalHeading() > ExtensionSubsystem.ExtensionConstants.SNAP_1 && robot.drivebaseSubsystem.getExternalHeading() < ExtensionSubsystem.ExtensionConstants.SNAP_2))));
        turretAdjustRightButton.whilePressed(new TurretTranslateCommand(robot.extensionSubsystem,   -0.05, ()-> DRIVE_ENABLED && (RobotConstants.getAlliance()== Alliance.RED ^ (robot.drivebaseSubsystem.getExternalHeading() > ExtensionSubsystem.ExtensionConstants.SNAP_1 && robot.drivebaseSubsystem.getExternalHeading() < ExtensionSubsystem.ExtensionConstants.SNAP_2))));

    }


}

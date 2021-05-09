package org.firstinspires.ftc.teamcode;

import com.technototes.control.gamepad.GamepadStick;
import com.technototes.library.command.Command;
import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandBinding;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;

import org.firstinspires.ftc.teamcode.commands.autonomous.SendOneRingToShooterCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.commands.turret.TurretRotateLeftCommand;
import org.firstinspires.ftc.teamcode.commands.turret.TurretRotateRightCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseThenRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerThenOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRotateLeftCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRotateRightCommand;

/** Class for driver controls
 *
 */
public class OperatorInterface {
    /** The robot
     *
     */
    public Robot robot;

    /** Gamepads
     *
     */
    public CommandGamepad driverGamepad, codriverGamepad;

    /** Buttons for intake
     * mainbutton toggles between intaking in and off, and spit just extakes
     */
    public CommandButton intakeMainButton, intakeSpitButton;

    public CommandButton wobbleUpButton, wobbleDownButton;

    //public CommandButton turretLeftButton, turretRightButton;

    public CommandButton firePrepButton;
    public CommandAxis fireAxis;

    public CommandButton powerButton;
    public CommandAxis powerAxis;

    public GamepadStick driveLStick, driveRStick;
    //public CommandButton turboModeButton, snailModeButton;

    public CommandButton resetGyroButton;

    public OperatorInterface(CommandGamepad driver, CommandGamepad codriver, Robot r){

        //instantiate objects
        driverGamepad = driver;
        codriverGamepad = codriver;
        robot = r;

        //set buttons
        intakeMainButton = driverGamepad.a;
        intakeSpitButton = driverGamepad.b;

        //testButton = driverGamepad.x;
        wobbleUpButton = driverGamepad.y;
        wobbleDownButton = driverGamepad.x;
//        wobbleLeftButton = driverGamepad.dpadLeft;
//        wobbleRightButton = driverGamepad.dpadRight;

//        turretLeftButton = driverGamepad.dpadLeft;
//        turretRightButton = driverGamepad.dpadRight;

        firePrepButton = driverGamepad.leftBumper;
        fireAxis = driverGamepad.leftTrigger;
        //to actually fire
        fireAxis.setTriggerThreshold(0.1);

        powerButton = driverGamepad.rightBumper;
        powerAxis = driverGamepad.rightTrigger;
        powerAxis.setTriggerThreshold(0.1);

        driveLStick = driverGamepad.leftStick;
        driveRStick = driverGamepad.rightStick;


        resetGyroButton = driverGamepad.rightStickButton;


//        wobbleDownButton.whenPressed(new WobbleLowerThenOpenCommand(robot.wobbleSubsystem));
//        wobbleUpButton.whenPressed(new WobbleCloseThenRaiseCommand(robot.wobbleSubsystem));

//        wobbleLeftButton.whenPressed(new WobbleRotateLeftCommand(robot.wobbleSubsystem)).whenReleased(new Command().addRequirements(robot.wobbleSubsystem));
//        wobbleRightButton.whenPressed(new WobbleRotateRightCommand(robot.wobbleSubsystem)).whenReleased(new Command().addRequirements(robot.wobbleSubsystem));

        //intake commands
        intakeMainButton.whenPressed(new IntakeInCommand(robot.intakeSubsystem));

        intakeSpitButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem))
                .whenReleased(new IntakeStopCommand(robot.intakeSubsystem));

        powerButton.whilePressed(new ShooterSetFlapCommand(robot.shooterSubsystem, ()->1));

        firePrepButton.whenPressed(new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0.85))
                .whilePressed(new ShooterSetSpeedCommand(robot.shooterSubsystem, ()->1330))
                .whilePressed(new VisionAlignCommand(robot.turretSubsystem, robot.visionAimSubsystem).asConditional(()->!powerButton.getAsBoolean()));
        firePrepButton.whenPressed(new ParallelCommandGroup(
                new InstantCommand(()->robot.drivebaseSubsystem.speed = 0.7),
                new SequentialCommandGroup(new IntakeInCommand(robot.intakeSubsystem), new WaitCommand(0.4), new IntakeStopCommand(robot.intakeSubsystem))))
                .schedule(()->fireAxis.getAsBoolean()&&firePrepButton.getAsBoolean() && robot.shooterSubsystem.getVelocity()>=1300, new SendOneRingToShooterCommand(robot.indexSubsystem, ()->1-fireAxis.getAsDouble()))  //new IndexPivotDownCommand(robot.indexSubsystem))
                .whenReleased(new ParallelCommandGroup(
                        new InstantCommand(()->robot.drivebaseSubsystem.speed = 1),
                        new ShooterSetSpeedCommand(robot.shooterSubsystem, ()->800),
                        new InstantCommand(()->robot.turretSubsystem.setTurretPosition(0.5))
                ));


        driverGamepad.leftStickButton.whilePressed(new ShooterStopCommand(robot.shooterSubsystem));
        //drive command
        resetGyroButton.whileReleased(new DriveCommand(robot.drivebaseSubsystem, driveLStick, driveRStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));

//        turretLeftButton.whenPressed(new WobbleRotateLeftCommand(robot.wobbleSubsystem, turretLeftButton));
//        turretRightButton.whenPressed(new WobbleRotateRightCommand(robot.wobbleSubsystem, turretRightButton));

        driverGamepad.dpadDown.whenPressed(new InstantCommand(robot.stickSubsystem::lower));
        driverGamepad.dpadUp.whenPressed(new InstantCommand(robot.stickSubsystem::raise));


        //TODO pass subsystems into commands automatically
        wobbleUpButton.whenPressed(new WobbleCloseCommand(robot.wobbleSubsystem)
                .then(new WobbleRaiseCommand(robot.wobbleSubsystem)
                        .with(new InstantCommand(()->robot.wobbleSubsystem.setTurretPosition(0.5)))));
        wobbleDownButton.whenPressed(new WobbleOpenCommand(robot.wobbleSubsystem)
                .sleep(1)
                .then(new WobbleLowerCommand(robot.wobbleSubsystem))
                        .then(new InstantCommand(()->robot.wobbleSubsystem.setTurretPosition(0))));



    }
}

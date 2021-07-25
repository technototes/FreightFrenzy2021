package org.firstinspires.ftc.teamcode;

import com.technototes.control.gamepad.GamepadStick;
import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.control.gamepad.CommandAxis;
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
import org.firstinspires.ftc.teamcode.commands.sticks.StickLowerCommand;
import org.firstinspires.ftc.teamcode.commands.sticks.StickRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRotateCommand;

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


    public CommandButton firePrepButton;
    public CommandAxis fireAxis;

    public CommandButton powerButton;
    public CommandAxis powerAxis;

    public GamepadStick driveLStick, driveRStick;

    public CommandButton resetGyroButton;

    public OperatorInterface(CommandGamepad driver, CommandGamepad codriver, Robot r){

        //instantiate objects
        driverGamepad = driver;
        codriverGamepad = codriver;
        robot = r;

        //set buttons
        intakeMainButton = driverGamepad.a;
        intakeSpitButton = driverGamepad.b;

        wobbleUpButton = driverGamepad.y;
        wobbleDownButton = driverGamepad.x;

        firePrepButton = driverGamepad.leftBumper;
        fireAxis = driverGamepad.leftTrigger.setTriggerThreshold(0.1);

        powerButton = driverGamepad.rightBumper;
        powerAxis = driverGamepad.rightTrigger.setTriggerThreshold(0.1);

        driveLStick = driverGamepad.leftStick;
        driveRStick = driverGamepad.rightStick;


        resetGyroButton = driverGamepad.rightStickButton;


        //intake commands
        intakeMainButton.whenPressed(new IntakeInCommand(robot.intakeSubsystem));

        intakeSpitButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem))
                .whenReleased(new IntakeStopCommand(robot.intakeSubsystem));

        powerButton.whilePressed(new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0));

        firePrepButton.whenPressed(new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0.88))
                .whenPressed(new SendOneRingToShooterCommand(robot.indexSubsystem, ()->1-fireAxis.getAsDouble()).asConditional(()->fireAxis.getAsBoolean() && robot.shooterSubsystem.getVelocity()>=1300))
                .whilePressed(new ShooterSetSpeedCommand(robot.shooterSubsystem, ()->1350))
                .whilePressed(new VisionAlignCommand(robot.turretSubsystem, robot.visionAimSubsystem).asConditional(()->!powerButton.getAsBoolean()))
                .whenReleased(new ParallelCommandGroup(()->robot.drivebaseSubsystem.speed = 1,()->robot.turretSubsystem.setTurretPosition(0.5)));

        driverGamepad.leftStickButton.whilePressed(new ShooterStopCommand(robot.shooterSubsystem));
        //drive command
        resetGyroButton.whileReleased(new DriveCommand(robot.drivebaseSubsystem, driveLStick, driveRStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));

        driverGamepad.dpadDown.whenPressed(new StickLowerCommand(robot.stickSubsystem));
        driverGamepad.dpadUp.whenPressed(new StickRaiseCommand(robot.stickSubsystem));

        wobbleUpButton.whenPressed(new WobbleCloseCommand(robot.wobbleSubsystem)
                .andThen(new WobbleRaiseCommand(robot.wobbleSubsystem)
                        .alongWith(new WobbleRotateCommand(robot.wobbleSubsystem, 0.5))));
        wobbleDownButton.whenPressed(new WobbleOpenCommand(robot.wobbleSubsystem)
                .sleep(1)
                .andThen(new WobbleLowerCommand(robot.wobbleSubsystem))
                        .andThen(new WobbleRotateCommand(robot.wobbleSubsystem, 0)));


    }
}

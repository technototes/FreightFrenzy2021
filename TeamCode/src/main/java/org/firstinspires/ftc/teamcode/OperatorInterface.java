package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.technototes.control.gamepad.GamepadStick;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;

import org.firstinspires.ftc.teamcode.commands.autonomous.SendOneRingToShooterCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TurretFollowCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.AlignToShootCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.index.IndexPivotDownCommand;
import org.firstinspires.ftc.teamcode.commands.index.IndexPivotUpCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseThenRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerThenOpenCommand;

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

    public CommandButton testButton, wobbleArmButton;

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

        testButton = driverGamepad.x;
        wobbleArmButton = driverGamepad.y;

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



//        powerAxis.whilePressed(new InstantCommand(()->robot.shooterSubsystem.setAngle(powerAxis.getAsDouble())))
//                .whileReleased(new TurretFollowCommand(robot.drivebaseSubsystem, robot.shooterSubsystem));
        wobbleArmButton.whenToggled(new WobbleLowerThenOpenCommand(robot.wobbleSubsystem))
                .whenInverseToggled(new WobbleCloseThenRaiseCommand(robot.wobbleSubsystem));

        //testButton.whenPressed(new TestSplineCommand(robot.drivebaseSubsystem));

        //intake commands
        intakeMainButton.whenPressed(new IntakeInCommand(robot.intakeSubsystem));
                //.whenInverseToggled(new IntakeStopCommand(robot.intakeSubsystem));

        intakeSpitButton.whenPressed(new IntakeOutCommand(robot.intakeSubsystem))
                .whenReleased(new IntakeStopCommand(robot.intakeSubsystem));

        powerButton.whilePressed(new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0.23));
//        powerButton.whilePressed(new ShooterSetFlapCommand(robot.shooterSubsystem, powerAxis));
        firePrepButton.whenPressed(new ParallelCommandGroup(
                new IndexPivotUpCommand(robot.indexSubsystem),
                new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0.70),
                new IntakeStopCommand(robot.intakeSubsystem)))
                .schedule(()->fireAxis.getAsBoolean()&&firePrepButton.getAsBoolean(), new SendOneRingToShooterCommand(robot.indexSubsystem, ()->1-fireAxis.getAsDouble()))   //new IndexPivotDownCommand(robot.indexSubsystem))
                .whenReleased(new IndexPivotDownCommand(robot.indexSubsystem))
                .whenReleased(new ShooterStopCommand(robot.shooterSubsystem));
                //.whenReleased(new IntakeInCommand(robot.intakeSubsystem));
        //fireAxis.whenReleased(new ArmRetractCommand(robot.indexSubsystem));
        //drive command
        testButton.whileReleased(new DriveCommand(robot.drivebaseSubsystem, driveLStick, driveRStick))
                .whilePressed(new VisionAlignCommand(robot.drivebaseSubsystem, robot.visionSubsystem));

//        snailModeButton.whenPressed(new SnailSpeedCommand(robot.drivebaseSubsystem))
//                .whenReleased(new NormalSpeedCommand(robot.drivebaseSubsystem));
//        turboModeButton.whenPressed(new TurboSpeedCommand(robot.drivebaseSubsystem))
//                .whenReleased(new NormalSpeedCommand(robot.drivebaseSubsystem));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));



    }
}

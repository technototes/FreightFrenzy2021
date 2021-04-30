package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.AlignToShootCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

/**
 * Main OpMode
 */
@Config
@Disabled
@Autonomous(name = "AutoV1")
public class AutoV1 extends CommandOpMode implements Loggable {

    public static final double ADDITIONAL_DELAY_BETWEEN_SHOTS = 0.1;

    public static final double INITIAL_SHOOTING_ROTATION = -12;
    public static final double INITIAL_FLAP_ANGLE = 0.2;

    public static final double SECONDARY_SHOOTING_X = 40;
    public static final double SECONDARY_SHOOTING_Y = 0;
    public static final double SECONDARY_SHOOTING_TANGENT = 0;
    public static final double SECONDARY_SHOOTING_ROTATION = -15;

    public static final double SECONDARY_FLAP_ANGLE = 0.3;

    public static final double FIRST_WOBBLE_DROP_X = 115;
    public static final double FIRST_WOBBLE_DROP_Y = 8;
    public static final double FIRST_WOBBLE_DROP_ROTATION = 90;

    public static final double SECOND_WOBBLE_GRAB_X = 19;
    public static final double SECOND_WOBBLE_GRAB_Y = 30;
    public static final double SECOND_WOBBLE_GRAB_ROTATION = 0;

    public static final double SECOND_WOBBLE_DROP_X = 110;
    public static final double SECOND_WOBBLE_DROP_Y = 8;
    public static final double SECOND_WOBBLE_DROP_ROTATION = 90;

    public static final double END_X = 80;
    public static final double END_Y = 10;
    public static final double END_ROTATION = 82;


    /**
     * The robot
     */
    @LogConfig.Disabled
    public Robot robot;


    @Override
    public void uponInit() {
        robot = new Robot();
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
    }

    @Override
    public void uponStart() {
        //prep to shoot
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new TurnCommand(robot.drivebaseSubsystem, INITIAL_SHOOTING_ROTATION),//-10
                                new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                                new ShooterSetFlapCommand(robot.shooterSubsystem, () -> INITIAL_FLAP_ANGLE),//0.4
                                new WaitCommand(0.3)
                        ),
                        //shoot 3 rings
                        //new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                        new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                        new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                        new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                        new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                        new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                        new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                        new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                        new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                        new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                        new WaitCommand(0.5),

                        //move to stack
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(new WaitCommand(1), new ShooterStopCommand(robot.shooterSubsystem)),
                                new IntakeInCommand(robot.intakeSubsystem),
                                new SplineCommand(robot.drivebaseSubsystem, FIRST_WOBBLE_DROP_X, FIRST_WOBBLE_DROP_Y, 20, FIRST_WOBBLE_DROP_ROTATION)
                        ), //48 12 0 -15
                        //prep to shoot
                    //snew StrafeCommand(robot.drivebaseSubsystem, FIRST_WOBBLE_DROP_X, FIRST_WOBBLE_DROP_Y, FIRST_WOBBLE_DROP_ROTATION)),// 120 1 9
                        //drop 1st wobble
                        new ParallelCommandGroup(new WobbleOpenCommand(robot.wobbleSubsystem), new WobbleLowerCommand(robot.wobbleSubsystem)),
                        //move to 2nd wobble
                        //new StrafeCommand(robot.drivebaseSubsystem, SECOND_WOBBLE_GRAB_X, SECOND_WOBBLE_GRAB_Y, SECOND_WOBBLE_GRAB_ROTATION),//s30 29 -30
                        new SplineCommand(robot.drivebaseSubsystem, SECOND_WOBBLE_GRAB_X, SECOND_WOBBLE_GRAB_Y, -120, 0),//s30 29 -30

                        //grab 2nd wobble
                        new WobbleCloseCommand(robot.wobbleSubsystem),
                        new SplineCommand(robot.drivebaseSubsystem, SECONDARY_SHOOTING_X, SECONDARY_SHOOTING_Y, 0),
                new ParallelCommandGroup(
                                        new TurnCommand(robot.drivebaseSubsystem, SECONDARY_SHOOTING_ROTATION),
                                        new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                                        new IntakeStopCommand(robot.intakeSubsystem),
                                        new ShooterSetFlapCommand(robot.shooterSubsystem, () -> SECONDARY_FLAP_ANGLE)//0.3
                                ),
                        //shoot 3 rings
                                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                                new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                                new WaitCommand(ADDITIONAL_DELAY_BETWEEN_SHOTS), //0.2
                                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                        //move to drop point
                                new ParallelCommandGroup(
                                        new ShooterStopCommand(robot.shooterSubsystem)
                                        ),

                        //move to drop point
                        new ParallelCommandGroup(new StrafeCommand(robot.drivebaseSubsystem, SECOND_WOBBLE_DROP_X, SECOND_WOBBLE_DROP_Y, SECOND_WOBBLE_DROP_ROTATION),
                                new WobbleRaiseCommand(robot.wobbleSubsystem)), //115 1 90
                        //drop 2nd wobble
                        new SequentialCommandGroup(new WobbleLowerCommand(robot.wobbleSubsystem), new WobbleOpenCommand(robot.wobbleSubsystem)),
                        //goto line
                        new ParallelCommandGroup(
                                new StrafeCommand(robot.drivebaseSubsystem, END_X, END_Y, END_ROTATION), //80 10 82
                                new SequentialCommandGroup(new WobbleRaiseCommand(robot.wobbleSubsystem), new WobbleCloseCommand(robot.wobbleSubsystem))),
                        new InstantCommand(() -> robot.drivebaseSubsystem.setPoseEstimate(new Pose2d())),
                        new InstantCommand(this::terminate)
                ));
    }
}

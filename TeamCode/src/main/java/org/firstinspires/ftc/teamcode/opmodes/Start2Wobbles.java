package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobbleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobbleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobbleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.ParkCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.PrepToShootCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.SendOneRingToShooterCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;
@Disabled
@Autonomous(name = "Start2Wobbles")
public class Start2Wobbles extends CommandOpMode implements Loggable {
    /**
     * The robot
     */
    @LogConfig.Disabled
    public Robot robot;

    public AutoState state;

    @Override
    public void uponInit() {
        robot = new Robot();
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
        robot.wobbleSubsystem.setArmPosition(WobbleSubsystem.ArmPosition.RAISED);
        state = new AutoState(AutoState.Team.RED);
        state.setStackSize(AutoState.StackSize.FOUR);
    }

    @Override
    public void uponStart() {
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new TurnCommand(robot.drivebaseSubsystem, state.corrected(AutoState.INITIAL_SHOOTING_ROTATION)),
                        new PrepToShootCommand(robot.indexSubsystem, robot.shooterSubsystem, 0.8, AutoState.INITIAL_FLAP_ANGLE),
//                        new SendRingsToShooterCommand(robot.indexSubsystem, 6),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new ShooterStopCommand(robot.shooterSubsystem),
                        new DeliverFirstWobbleCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new ObtainSecondWobbleCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new StrafeCommand(robot.drivebaseSubsystem, state.correctedPos(30, 0, 0)),
                        new DeliverSecondWobbleCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new ParkCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new InstantCommand(this::terminate)
                )
        );
    }
}

package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Pair;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Log;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.GetStackSizeCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AimAndShootCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobbleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobbleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.IntakeStackCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobbleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.ParkCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.PathToShootCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.PrepToShootCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.SendOneRingToShooterCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;
@Autonomous(name = "WobblesThenAllRings")
public class WobblesThenAllRings extends CommandOpMode implements Loggable {
    /**
     * The robot
     */
    public Robot robot;

    public AutoState state;

    @Override
    public void uponInit() {
        CommandScheduler.resetScheduler();
        robot = new Robot();
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
        robot.wobbleSubsystem.setArmPosition(WobbleSubsystem.ArmPosition.RAISED);
        state = new AutoState(AutoState.Team.RED);
        state.setStackSize(AutoState.StackSize.FOUR);
            //        CommandScheduler.getInstance().scheduleForState(new GetStackSizeCommand(robot.visionSubsystem, state),
    //                () -> true, OpModeState.INIT);
    }

    @Override
    public void uponStart() {
        robot.turretSubsystem.raise();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new DeliverFirstWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new PathToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, state),
                        new AimAndShootCommand(robot.indexSubsystem, robot.turretSubsystem, robot.visionAimSubsystem, robot.shooterSubsystem),
                        new ShooterSetSpeedCommand(robot.shooterSubsystem, ()->800),
                        new IntakeStackCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, state),
                        new InstantCommand(()->robot.wobbleSubsystem.setTurretPosition(1)),
                        new ObtainSecondWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new PathToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, state),
                        new AimAndShootCommand(robot.indexSubsystem, robot.turretSubsystem, robot.visionAimSubsystem, robot.shooterSubsystem),
                        new DeliverSecondWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new ParkCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new InstantCommand(this::terminate)
        ));
    }
}
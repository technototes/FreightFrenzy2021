package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Color;
import com.technototes.logger.Log;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;
import com.technototes.logger.entry.Entry;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.GetStackSizeCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ParkCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.PrepToShootCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.SendOneRingToShooterCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

@Autonomous(name = "WobblesThenStartingRings")
public class WobblesThenStartingRings extends CommandOpMode implements Loggable {
    /**
     * The robot
     */
    //@LogConfig.Disabled
    public Robot robot;

    public AutoState state;

    @Override
    public void uponInit() {
        robot = new Robot();
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
        robot.wobbleSubsystem.setArmPosition(WobbleSubsystem.ArmPosition.RAISED);
        state = new AutoState(AutoState.Team.RED);
        state.setStackSize(AutoState.StackSize.FOUR);
        CommandScheduler.getInstance().scheduleForState(new GetStackSizeCommand(robot.visionSubsystem, state),
                () -> true, OpModeState.INIT);
    }

    @Override
    public void uponStart() {
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new DeliverFirstWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new ObtainSecondWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new DeliverSecondWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        //shoot
                        new ParallelCommandGroup(
                                new StrafeCommand(robot.drivebaseSubsystem, state.correctedPos(60, 6, -5)),
                                new PrepToShootCommand(robot.indexSubsystem, robot.shooterSubsystem, 0.8, 0.45),
                                new SequentialCommandGroup(new IntakeInCommand(robot.intakeSubsystem), new WaitCommand(0.4),
                                        new IntakeStopCommand(robot.intakeSubsystem))
                        ),
                        new SendOneRingToShooterCommand(robot.indexSubsystem, 0.2),
                        new SendOneRingToShooterCommand(robot.indexSubsystem, 0.2),
                        new SendOneRingToShooterCommand(robot.indexSubsystem, 0.2),
                        new SendOneRingToShooterCommand(robot.indexSubsystem, 0.2),

                        new ParkCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new ShooterStopCommand(robot.shooterSubsystem),
                        new InstantCommand(this::terminate)
                )
        );
    }

    @Override
    public void universalLoop() {
//        System.out.println(getStackSize());
//        telemetry.addLine(""+getStackSize());
    }
}

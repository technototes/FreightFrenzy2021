package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

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
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

@Autonomous(name = "WobblesThenStartingRings")
public class WobblesThenStartingRings extends CommandOpMode implements Loggable {
    /**
     * The robot
     */
    @LogConfig.Disabled
    public Robot robot;

    public AutoState.StackSize getStackSize() {
        return state.stackSize;
    }

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
                        //new StrafeCommand(robot.drivebaseSubsystem, state.correctedPos(30, 0, 0)),
                        new DeliverSecondWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        //shoot
                        new ParallelCommandGroup(
                                new StrafeCommand(robot.drivebaseSubsystem, state.correctedPos(60, 15, -10)),
                                new PrepToShootCommand(robot.indexSubsystem, robot.shooterSubsystem, 0.8, 0.3)
                                ),
                        new WaitCommand(1),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),
                        new SendOneRingToShooterCommand(robot.indexSubsystem),

                        new ParkCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new InstantCommand(this::terminate)
                )
        );
    }

    @Override
    public void universalLoop() {
        System.out.println(getStackSize());
    }
}

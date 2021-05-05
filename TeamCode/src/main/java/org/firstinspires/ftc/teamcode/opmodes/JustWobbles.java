package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.GetStackSizeCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobble2Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ParkCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

@Autonomous(name = "JustWobbles")
public class JustWobbles extends CommandOpMode implements Loggable {
    /**
     * The robot
     */
    @LogConfig.Disabled
    public Robot robot;

    public AutoState.StackSize getStackSize(){
        return state.stackSize;
    }

    public AutoState state;

    @Override
    public void uponInit() {
        robot = new Robot();
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
        state.setStackSize(AutoState.StackSize.FOUR);
        CommandScheduler.getInstance().scheduleForState(new GetStackSizeCommand(robot.visionStackSubsystem, state),
                ()->true, OpModeState.INIT);
    }

    @Override
    public void uponStart() {
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new DeliverFirstWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new ObtainSecondWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                        new DeliverSecondWobble2Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
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

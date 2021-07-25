package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.GetStackSizeCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.commands.autonomous.BouncebackCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.PowershotCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

@Autonomous(name = "Bounceback")
public class Bounceback extends CommandOpMode implements Loggable {
    /**
     * The robot
     */
    public Robot robot;

    public AutoState state;

    @Override
    public void uponInit() {
        CommandScheduler.resetScheduler();
        robot = new Robot(true);
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
        robot.wobbleSubsystem.setArmPosition(WobbleSubsystem.ArmPosition.RAISED);
        state = new AutoState(AutoState.Team.RED);
        state.setStackSize(AutoState.StackSize.FOUR);
                    CommandScheduler.getInstance().scheduleForState(new GetStackSizeCommand(robot.visionStackSubsystem, state),
                    () -> true, OpModeState.INIT);
    }

    @Override
    public void uponStart() {
        robot.turretSubsystem.raise();
        robot.turretSubsystem.setTurretPosition(1);
            CommandScheduler.getInstance().schedule(
                    new SequentialCommandGroup(
                            new PowershotCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, robot.intakeSubsystem, robot.indexSubsystem, robot.turretSubsystem, state),
                            new BouncebackCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, robot.intakeSubsystem, robot.indexSubsystem, robot.turretSubsystem, state),
                            new ObtainSecondWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                            new OldInstantCommand(this::terminate)
                    ));

    }
}

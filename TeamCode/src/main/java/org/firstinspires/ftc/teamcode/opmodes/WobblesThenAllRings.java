package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.GetStackSizeCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AimAndShootCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverFirstWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.DeliverSecondWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ObtainSecondWobble3Command;
import org.firstinspires.ftc.teamcode.commands.autonomous.ParkCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.PathToShootCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
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
        robot = new Robot(true);
        robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED);
        robot.wobbleSubsystem.setArmPosition(WobbleSubsystem.ArmPosition.RAISED);
        state = new AutoState(AutoState.Team.RED);
        state.setStackSize(AutoState.StackSize.FOUR);
        CommandScheduler.getInstance().scheduleInit(new GetStackSizeCommand(robot.visionStackSubsystem, state));
    }

    @Override
    public void uponStart() {
        robot.turretSubsystem.raise();
        robot.turretSubsystem.setTurretPosition(1);

            CommandScheduler.getInstance().schedule(
                    new SequentialCommandGroup(
                            new DeliverFirstWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                            new ShooterSetSpeedCommand(robot.shooterSubsystem, () -> 800),
                            //new PowershotCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, robot.intakeSubsystem, robot.indexSubsystem, robot.turretSubsystem, state),
                            //new ShooterSetSpeedCommand(robot.shooterSubsystem, () -> 1),
                            new PathToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, robot.intakeSubsystem, robot.turretSubsystem, state),
                            new AimAndShootCommand(robot.intakeSubsystem, robot.indexSubsystem, robot.turretSubsystem, robot.visionAimSubsystem, robot.shooterSubsystem),
                            new ShooterSetSpeedCommand(robot.shooterSubsystem, () -> 800)
                                .alongWith(new StrafeCommand(robot.drivebaseSubsystem, new Pose2d(50, 30, 0))),
                                //.with(new IntakeStackCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, state)),
                            new ObtainSecondWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                            //new IntakeInCommand(robot.intakeSubsystem),
                            //new ShooterSetSpeedCommand(robot.shooterSubsystem, () -> 800),
                            new PathToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem, robot.intakeSubsystem, robot.turretSubsystem, state)
                                    .alongWith(new WobbleRaiseCommand(robot.wobbleSubsystem)),

                            new AimAndShootCommand(robot.intakeSubsystem, robot.indexSubsystem, robot.turretSubsystem, robot.visionAimSubsystem, robot.shooterSubsystem)
                                    .alongWith(new IntakeStopCommand(robot.intakeSubsystem)),
                            new ShooterSetSpeedCommand(robot.shooterSubsystem, () -> 0)
                                    .alongWith(new DeliverSecondWobble3Command(robot.drivebaseSubsystem, robot.wobbleSubsystem, state)
                                            .alongWith(new OldInstantCommand(() -> robot.turretSubsystem.setTurretPosition(1)))),
                            new ParkCommand(robot.drivebaseSubsystem, robot.wobbleSubsystem, state),
                            new OldInstantCommand(this::terminate)
                    ));
    }
}

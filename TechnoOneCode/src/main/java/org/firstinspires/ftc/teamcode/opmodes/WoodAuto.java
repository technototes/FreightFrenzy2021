package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.path.command.NewTrajectoryCommand;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;

@Autonomous(name="auto")
@SuppressWarnings("unused")
public class WoodAuto extends CommandOpMode implements Loggable {
    public Robot robot;


    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();

        Trajectory toDeliverPreload = robot.drivebaseSubsystem.trajectoryBuilder(new Pose2d())
                .strafeTo(new Vector2d(15, 15))
                .build();


        Trajectory toCollect = robot.drivebaseSubsystem.trajectoryBuilder(robot.drivebaseSubsystem.getPoseEstimate(), true)
                .splineTo(new Vector2d(2, -20), Math.toRadians(270))
                .splineTo(new Vector2d(6, -30), Math.toRadians(70))
                .build();

        Trajectory toDeposit = robot.drivebaseSubsystem.trajectoryBuilder(robot.drivebaseSubsystem.getPoseEstimate())
                .splineTo(new Vector2d(2, -20), Math.toRadians(-90))
                .splineTo(new Vector2d(15, 10), Math.toRadians(20))
                .build();

        Trajectory toPark = robot.drivebaseSubsystem.trajectoryBuilder(robot.drivebaseSubsystem.getPoseEstimate(), true)
                .splineTo(new Vector2d(2, -10), Math.toRadians(-90))
                .splineTo(new Vector2d(2, -20), Math.toRadians(-90))
                .build();

        CommandScheduler.getInstance().scheduleForState(new SequentialCommandGroup(
                new NewTrajectoryCommand(robot.drivebaseSubsystem, toDeliverPreload).alongWith(new ArmExtendCommand(robot.depositSubsystem)),
                new DumpCommand(robot.depositSubsystem),
                new NewTrajectoryCommand(robot.drivebaseSubsystem, toCollect).alongWith(new ArmRetractCommand(robot.depositSubsystem)),
                new NewTrajectoryCommand(robot.drivebaseSubsystem, toDeposit).alongWith(new ArmExtendCommand(robot.depositSubsystem)),
                new DumpCommand(robot.depositSubsystem),
                new NewTrajectoryCommand(robot.drivebaseSubsystem, toPark).alongWith(new ArmRetractCommand(robot.depositSubsystem)),
                this::terminate
        ), OpModeState.RUN);

    }

    @Override
    public void uponStart() {
    }
}

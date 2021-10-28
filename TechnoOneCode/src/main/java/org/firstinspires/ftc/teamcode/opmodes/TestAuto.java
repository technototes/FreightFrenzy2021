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
import com.technototes.path.command.NewNewTrajectoryCommand;
import com.technototes.path.command.NewTrajectoryCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;

@Autonomous(name="test")
@SuppressWarnings("unused")
public class TestAuto extends CommandOpMode implements Loggable {
    public Robot robot;


    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();
        robot.drivebaseSubsystem.setPoseEstimate(new Pose2d(0, -65, Math.toRadians(90)));

        TrajectorySequence s = robot.drivebaseSubsystem.trajectorySequenceBuilder()
                .lineToSplineHeading(new Pose2d(0, -40, Math.toRadians(120)))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(44, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(46, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(48, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(50, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(35, -64))
                .build();

        CommandScheduler.getInstance().scheduleForState(new SequentialCommandGroup(
                new NewNewTrajectoryCommand(robot.drivebaseSubsystem, s),
                this::terminate
        ), OpModeState.RUN);

    }

    @Override
    public void uponStart() {
    }
}

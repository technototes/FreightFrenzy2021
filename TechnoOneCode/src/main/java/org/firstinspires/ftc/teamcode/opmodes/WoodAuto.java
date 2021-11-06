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
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
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
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.START_SELECT.get());


        CommandScheduler.getInstance().scheduleForState(new SequentialCommandGroup(
                new AutonomousCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.depositSubsystem, robot.visionSubsystem),
                this::terminate
        ), OpModeState.RUN);

    }

}

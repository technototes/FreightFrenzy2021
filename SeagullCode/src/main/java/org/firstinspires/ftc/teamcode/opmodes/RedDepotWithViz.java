package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.VisionCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoRedShippingHubCommandGroup;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;

//@Autonomous(name = "Red Depot")
@SuppressWarnings("unused")
public class RedDepotWithViz extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        AutonomousConstants.ALLIANCE = Alliance.RED;
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware();
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.RedConstants.DEPOT_START);
        CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSubsystem));
        CommandScheduler.getInstance().scheduleForState(
                new AutoRedShippingHubCommandGroup(robot.drivebaseSubsystem, robot.dumpSubsystem, robot.intakeSubsystem),
                CommandOpMode.OpModeState.RUN);
    }
}

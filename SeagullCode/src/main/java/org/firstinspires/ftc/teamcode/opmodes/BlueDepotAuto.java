package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoBlueShippingHubCommandGroup;

@Autonomous(name = "Blue Depot")
@SuppressWarnings("unused")
public class BlueDepotAuto extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        AutonomousConstants.ALLIANCE = Alliance.BLUE;
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware();
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.BlueConstants.DEPOT_START);

        CommandScheduler.getInstance().scheduleForState(
                new AutoBlueShippingHubCommandGroup(robot.drivebaseSubsystem, robot.dumpSubsystem, robot.intakeSubsystem, robot.visionSubsystem),
                CommandOpMode.OpModeState.RUN);
    }
}

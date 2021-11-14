/**
 * equivalent to CycleBlueAuto.java
 */
package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousPhaseCommand;

@Autonomous(name = "Blue Duck")
@SuppressWarnings("unused")
public class BlueDuckAuto extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        AutonomousConstants.ALLIANCE = Alliance.BLUE;
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware();
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.DUCK_START_SELECT.get());

        CommandScheduler.getInstance().scheduleForState(
                new AutonomousPhaseCommand(robot.drivebaseSubsystem, robot.carouselSubsystem, robot.dumpSubsystem),
                OpModeState.RUN);
    }
}

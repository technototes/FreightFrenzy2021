/**
 * copy from BlueDuckAuto.java
 */
package org.firstinspires.ftc.teamcode.opmodes;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousPhaseCommand;

@Autonomous(name = "Red Duck")
public class RedDuckAuto extends CommandOpMode implements Loggable{
    public Robot robot;

    @Override
    public void uponInit() {
        AutonomousConstants.ALLIANCE = Alliance.RED;
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.DUCK_START_SELECT.get());

        CommandScheduler.getInstance().scheduleForState(new AutonomousPhaseCommand(robot.drivebaseSubsystem, robot.carouselSubsystem, robot.bucketSubsystem), OpModeState.RUN);
    }
}

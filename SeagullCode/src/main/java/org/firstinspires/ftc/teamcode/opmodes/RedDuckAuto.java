/**
 * copy from BlueDuckAuto.java
 */
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoRedDuckCommandGroup;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;

@Autonomous(name = "Red Duck")
@SuppressWarnings("unused")
public class RedDuckAuto extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;

    @Override

    public void uponInit() {
        AutonomousConstants.ALLIANCE = Alliance.RED;
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware();
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.RedConstants.DUCK_START);

        CommandScheduler.getInstance().scheduleForState(new AutoRedDuckCommandGroup(robot.drivebaseSubsystem, robot.carouselSubsystem, robot.dumpSubsystem, robot.intakeSubsystem), OpModeState.RUN);
    }
}

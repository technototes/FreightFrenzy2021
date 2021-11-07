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
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousCycleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousDuckCommand;

@Autonomous(name="red duck")
@SuppressWarnings("unused")
public class DuckRedAuto extends CommandOpMode implements Loggable {
    public Robot robot;


    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        AutonomousConstants.ALLIANCE = Alliance.RED;
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.DUCK_START_SELECT.get());


        CommandScheduler.getInstance().scheduleForState(new AutonomousDuckCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.depositSubsystem, robot.visionSubsystem, robot.carouselSubsystem)  , OpModeState.RUN);

    }

}

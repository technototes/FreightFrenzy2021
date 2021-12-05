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
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousCycleCommand;
import org.firstinspires.ftc.teamcode.commands.vision.VisionBarcodeCommand;

@Autonomous(name="red cyc")
@SuppressWarnings("unused")
public class CycleRedAuto extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;


    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        hardware = new Hardware();
        robot = new Robot(hardware);
        AutonomousConstants.updateAlliance(Alliance.RED);
        robot.drivebaseSubsystem.setPoseEstimate(AutonomousConstants.CYCLE_START_SELECT.get());
        CommandScheduler.getInstance().scheduleInit(new VisionBarcodeCommand(robot.visionSubsystem));


        CommandScheduler.getInstance().scheduleForState(new AutonomousCycleCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.depositSubsystem, robot.visionSubsystem)  , OpModeState.RUN);

    }

}

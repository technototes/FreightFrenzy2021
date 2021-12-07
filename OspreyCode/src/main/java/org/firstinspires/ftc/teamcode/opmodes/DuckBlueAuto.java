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
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoDuckCommand;
import org.firstinspires.ftc.teamcode.commands.vision.VisionBarcodeCommand;

@Autonomous(name="blue duck")
@SuppressWarnings("unused")
public class DuckBlueAuto extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;


    @Override
    public void uponInit() {
        //MAYBE THIS WORKS


        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        hardware = new Hardware();
        robot = new Robot(hardware);
        RobotConstants.updateAlliance(Alliance.BLUE);
        robot.drivebaseSubsystem.setPoseEstimate(RobotConstants.DUCK_START_SELECT.get());
        CommandScheduler.getInstance().scheduleInit(new VisionBarcodeCommand(robot.visionSubsystem));


        CommandScheduler.getInstance().scheduleForState(new AutoDuckCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.depositSubsystem, robot.extensionSubsystem, robot.visionSubsystem, robot.carouselSubsystem)  , OpModeState.RUN);

    }

}

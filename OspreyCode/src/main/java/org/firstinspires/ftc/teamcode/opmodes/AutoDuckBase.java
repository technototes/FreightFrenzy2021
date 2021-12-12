package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoCycleCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoDuckCommand;
import org.firstinspires.ftc.teamcode.commands.vision.VisionBarcodeCommand;

public abstract class AutoDuckBase extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        RobotConstants.updateAlliance(Alliance.get(getClass()));
        hardware = new Hardware();
        robot = new Robot(hardware);
        CommandScheduler.getInstance().scheduleInit(new VisionBarcodeCommand(robot.visionSubsystem));
        CommandScheduler.getInstance().scheduleOnceForState(new AutoDuckCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem, robot.visionSubsystem, robot.carouselSubsystem)  , OpModeState.RUN);
    }
    @Autonomous
    @Alliance.Blue
    public static class DuckBlueAuto extends AutoDuckBase {}

    @Autonomous
    @Alliance.Red
    public static class DuckRedAuto extends AutoDuckBase {}

}

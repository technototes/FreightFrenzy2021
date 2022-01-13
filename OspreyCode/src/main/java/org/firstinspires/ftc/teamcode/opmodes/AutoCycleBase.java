package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoCycleCommand;
import org.firstinspires.ftc.teamcode.commands.vision.VisionBarcodeCommand;

public abstract class AutoCycleBase extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        RobotConstants.updateAlliance(Alliance.get(getClass()));
        hardware = new Hardware();
        robot = new Robot(hardware);
        CommandScheduler.getInstance().scheduleInit(new VisionBarcodeCommand(robot.visionSubsystem));
        CommandScheduler.getInstance().scheduleOnceForState(new AutoCycleCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem, robot.visionSubsystem), OpModeState.RUN);
        CommandScheduler.getInstance().scheduleInit(new WaitCommand(40).andThen(robot.speakerSubsystem::playJeopardy));
        CommandScheduler.getInstance().scheduleForState(new SequentialCommandGroup(robot.speakerSubsystem::playAmogus, new WaitCommand(40)), OpModeState.RUN);
    }
    @Override
    public void end() {
        hardware.speaker.stop();
    }

    @Autonomous(name="\uD83D\uDD35 ♻️ Blue Cycle")
    @Alliance.Blue
    public static class CycleBlueAuto extends AutoCycleBase {}

    @Autonomous(name="\uD83D\uDFE5 ♻️ Red Cycle")
    @Alliance.Red
    public static class CycleRedAuto extends AutoCycleBase {}

}

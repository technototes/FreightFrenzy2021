package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConstants;

public abstract class TeleOpBase extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;
    public Controls controls;

    @Override
    public void uponInit() {
        RobotConstants.updateAlliance(Alliance.get(getClass()));
        hardware = new Hardware();
        robot = new Robot(hardware);
        controls = new Controls(robot, driverGamepad, codriverGamepad);
    }

    @TeleOp(name="\uD83D\uDFE5 \uD83C\uDFAE Red TeleOp")
    @Alliance.Red
    public static class RedTeleOp extends TeleOpBase {}

    @TeleOp(name="\uD83D\uDD35 \uD83C\uDFAE Blue Teleop")
    @Alliance.Blue
    public static class BlueTeleOp extends TeleOpBase {}

}

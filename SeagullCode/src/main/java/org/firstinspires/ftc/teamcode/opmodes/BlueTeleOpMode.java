package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "BlueTeleOp")
@SuppressWarnings("unused")
public class BlueTeleOpMode extends CommandOpMode implements Loggable {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;
    @Override
    public void uponInit() {
        hardware = new Hardware();
        robot = new Robot(hardware);
        controls = new Controls(driverGamepad, robot, Alliance.BLUE);
    }
}

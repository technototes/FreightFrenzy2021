package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "TeleOp")
public class TeleOpMode extends CommandOpMode {
    public Robot robot;
    public Controls controls;

    @Override
    public void uponInit() {
        robot = new Robot();
        controls = new Controls(driverGamepad, robot);
    }
}

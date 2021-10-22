package org.firstinspires.ftc.samplecode.finch.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.samplecode.clawbot.OperatorInterface;
import org.firstinspires.ftc.samplecode.clawbot.Robot;

@Disabled
@TeleOp(name="tankop")
public class Teleop extends CommandOpMode {
    public Robot robot;
    public OperatorInterface operatorInterface;

    @Override
    public void uponInit() {
        robot = new Robot();
        operatorInterface = new OperatorInterface(driverGamepad, robot);

    }
}

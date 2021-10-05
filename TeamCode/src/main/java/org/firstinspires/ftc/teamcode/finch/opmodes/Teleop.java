package org.firstinspires.ftc.teamcode.finch.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.teamcode.finch.OperatorInterface;
import org.firstinspires.ftc.teamcode.finch.Robot;

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

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.teamcode.Hardware;
@TeleOp
public class TestOpMode extends CommandOpMode {
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware();
        driverGamepad.triangle.whenPressed(()->hardware.armServo.setPosition(0));
        driverGamepad.square.whenPressed(()->hardware.dumpServo.setPosition(0));
        driverGamepad.circle.whenPressed(()->hardware.turretServo.setPosition(0.5));
        driverGamepad.cross.whenPressed(()->hardware.slideServo.setPosition(0));
    }
}

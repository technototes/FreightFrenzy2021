package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.MockHardwareMap;
import org.firstinspires.ftc.teamcode.opmodes.TeleOpMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRobot {
    public LinearOpMode opMode;

    @BeforeEach
    public void setup(){
        opMode = new TeleOpMode();
        opMode.gamepad1 = new MockGamepad();
        opMode.gamepad2 = new MockGamepad();
        opMode.hardwareMap = new MockHardwareMap();
        opMode.telemetry = new MockTelemetry();
        opMode.start();
    }

    @Test
    public void run() throws InterruptedException {

        opMode.runOpMode();
    }
}

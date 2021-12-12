    package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.MockHardwareMap;
import org.firstinspires.ftc.teamcode.io.MockGamepadManager;
import org.firstinspires.ftc.teamcode.io.MockTelemetry;
import org.firstinspires.ftc.teamcode.opmodes.TeleOpBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MockRobot {
    public LinearOpMode opMode;
    public MockGamepadManager manager;
    public static boolean TELEMETRY = false;
    @BeforeEach
    public void setup(){
        opMode = new TeleOpBase.RedTeleOp();
        manager = new MockGamepadManager();
        opMode.gamepad1 = manager.driver;
        opMode.gamepad2 = manager.codriver;
        opMode.hardwareMap = new MockHardwareMap();
        opMode.telemetry = new MockTelemetry();
        opMode.start();
    }

    @Test
    public void run() throws InterruptedException {
        opMode.runOpMode();
    }
}

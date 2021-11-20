package org.firstinspires.ftc.teamcode.hardware;

import android.content.Context;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class MockHardwareMap extends HardwareMap {
    public MockHardwareMap() {
        super(null);
        put("flmotor", new MockMotor());
        put("frmotor", new MockMotor());
        put("rlmotor", new MockMotor());
        put("rrmotor", new MockMotor());
        put("imu", new MockIMU());
        put("ldistance", new MockDistanceSensor());
        put("rdistance", new MockDistanceSensor());
        put("fdistance", new MockDistanceSensor());
        voltageSensor.put("voltage", new MockVoltageSensor());

    }

}

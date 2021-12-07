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
        put("lift", new MockMotor());
        put("intake", new MockMotor());
        put("carousel", new MockMotor());
        put("ldump", new MockServo());
        put("rdump", new MockServo());
        put("arm", new MockServo());
        put("webcam", new MockWebcam());
        put("irange", new MockDistanceSensor());
        put("cap", new MockServo());

        voltageSensor.put("voltage", new MockVoltageSensor());

    }

}

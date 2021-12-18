package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.VoltageSensor;

public class MockVoltageSensor implements VoltageSensor {
    @Override
    public double getVoltage() {
        return 0;
    }

    @Override
    public Manufacturer getManufacturer() {
        return null;
    }

    @Override
    public String getDeviceName() {
        return null;
    }

    @Override
    public String getConnectionInfo() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {

    }

    @Override
    public void close() {

    }
}

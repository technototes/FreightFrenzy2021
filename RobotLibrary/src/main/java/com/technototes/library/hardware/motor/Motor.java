package com.technototes.library.hardware.motor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.HardwareDevice;
import com.technototes.library.hardware.Invertable;

import java.util.function.Supplier;

/** Class for motors
 * @author Alex Stedman
 * @param <T> The qualcomm hardware device interface
 */
public class Motor<T extends DcMotorSimple> extends HardwareDevice<T> implements Invertable<Motor<T>>, Supplier<Double> {
    private boolean invert = false;
    /** Create a motor
     *
     * @param device The hardware device
     */
    public Motor(T device) {
        super(device);
    }

    /** Create a motor
     *
     * @param deviceName The device name
     */
    public Motor(String deviceName) {
        super(deviceName);
    }


    @Override
    public boolean getInverted() {
        return invert;
    }

    @Override
    public Motor<T> setInverted(boolean inv) {
        getDevice().setDirection(inv ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        invert = inv;
        return this;
    }

    @Override
    public Motor<T> invert() {
        return setInverted(!getInverted());
    }

    public double getSpeed() {
        return device.getPower();
    }

    /** Set speed of motor  
     *
     * @param speed The speed of the motor
     */
    public void setSpeed(double speed) {
        device.setPower(Range.clip(speed, -1, 1));
    }


    @Override
    public Double get() {
        return getSpeed();
    }
}

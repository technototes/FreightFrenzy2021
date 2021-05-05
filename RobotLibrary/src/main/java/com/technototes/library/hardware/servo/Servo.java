package com.technototes.library.hardware.servo;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.*;
import com.technototes.logger.Log;
import com.technototes.logger.Stated;

/** Class for servos
 * @author Alex Stedman
 */
public class Servo extends HardwareDevice<com.qualcomm.robotcore.hardware.Servo> implements Sensored, Invertable<Servo>, Followable<Servo> {

    //public double pid_p, pid_i, pid_d;


    private boolean inverted = false;
    /** Create servo object
     *
     * @param device The servo
     */
    public Servo(com.qualcomm.robotcore.hardware.Servo device) {
        super(device);
    }

    /** Create servo object
     *
     * @param deviceName The device name in hardware map
     */
    public Servo(String deviceName) {
        super(deviceName);
    }

    /** Set position for the servo and return this
     *
     * @param position The servo position
     * @return this
     */
    public Servo setStartingPosition(double position) {
        setPosition(position);
        return this;
    }

    @Override
    public boolean getInverted() {
        return inverted;
    }

    @Override
    public Servo setInverted(boolean invert) {
        inverted = invert;
        return this;
    }

    /** Set servo position
     *
     * @param position The position to set the servo to
     */
    public void setPosition(double position) {
        position = Range.clip(position, 0, 1);
        getDevice().setPosition(!inverted ? position : 1-position);
    }

    @Log
    @Override
    public double getSensorValue() {
        return !inverted ? getDevice().getPosition() : 1-getDevice().getPosition();
    }

    /** Get servo position
     *
     * @return The servo position
     */
    public double getPosition(){
        return getSensorValue();
    }

    /** Set servo range
     *
     * @param min The minimum of the range
     * @param max The maximum of the range
     * @return this
     */
    public Servo setRange(double min, double max) {
        getDevice().scaleRange(min, max);
        return this;
    }
    @Deprecated
    @Override
    public Servo follow(Servo d) {
        return new ServoGroup(this, d);
    }


}

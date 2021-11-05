package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.*;

/**
 * Subsystem holding methods used for Intake commands. Intake will be responsible for bringing
 * freight in and out of our robot
 */
public class IntakeSubsystem implements Subsystem, Supplier<Double> {

    @Config
    public static class IntakeConstants{
        public static double INTAKE_IN_SPEED = 1;
        public static double INTAKE_OUT_SPEED = -1;
        public static double INTAKE_STOP_SPEED = 0;
        public static double DETECTION_DISTANCE = 1;
    }

    public Motor<DcMotorEx> motor;

    public RangeSensor rangeSensor;

    public IntakeSubsystem(Motor<DcMotorEx> m, RangeSensor rs){
        motor = m;
        rangeSensor = rs;
    }

    /**
     * Set the intake motor to run in at a constant speed
     */
    public void in(){
        motor.setSpeed(INTAKE_IN_SPEED);
    }

    /**
     * Set the intake motor to run out at a constant speed
     */
    public void out(){
        motor.setSpeed(INTAKE_OUT_SPEED);
    }

    /**
     * Set the intake motor to stop running
     */
    public void stop(){
        motor.setSpeed(INTAKE_STOP_SPEED);
    }

    public double getSensorDistance(){
        return rangeSensor.getSensorValue();
    }

    public boolean isNearTarget(){
        return getSensorDistance() < IntakeConstants.DETECTION_DISTANCE;
    }

    @Override
    public Double get() {
        return motor.getSpeed();
    }
}

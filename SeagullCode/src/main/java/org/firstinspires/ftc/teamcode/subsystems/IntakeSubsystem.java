package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class IntakeSubsystem implements Subsystem, Supplier<Double> {
  public static class IntakeConstant {
    public static double INTAKE_IN_SPEED = -0.85;
    public static double INTAKE_OUT_SPEED = 1.0;
    public static double INTAKE_STOP_SPEED = 0;
    public static double DETECTION_THRESHOLD = 2.0;
  }

  public enum State {
    STOP,
    IN,
    OUT
  }

  private final EncodedMotor<DcMotorEx> motor;

  private final RangeSensor rangeSensor;

  private State currentState = State.STOP;

  public IntakeSubsystem(EncodedMotor<DcMotorEx> m, RangeSensor r) {
    motor = m;
    motor.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    rangeSensor = r;
  }

  public void in() {
    motor.setSpeed(IntakeConstant.INTAKE_IN_SPEED);
    currentState = State.IN;
  }

  public void out() {
    motor.setSpeed(IntakeConstant.INTAKE_OUT_SPEED);
    currentState = State.OUT;
  }

  public void stop() {
    motor.setSpeed(IntakeConstant.INTAKE_STOP_SPEED);
    currentState = State.STOP;
  }

  private double getSensorDistance() {
    return rangeSensor.getSensorValue();
  }

  // Want:
  // startDetection, which starts reading the distance sensor every N cycles (the sensor is slow to read)
  // stopDetection, which stops reading the distance sensor
  // getSensorDistance returns the average over the past 3 sensor reads, to smooth out the noise
  // hasCargo uses the smoothed distance value
  public boolean hasCargo() {
    return getSensorDistance() < IntakeConstant.DETECTION_THRESHOLD;
  }

  @Override
  public Double get() {
    return motor.getSpeed();
  }

  public State getState() {
    return currentState;
  }
}
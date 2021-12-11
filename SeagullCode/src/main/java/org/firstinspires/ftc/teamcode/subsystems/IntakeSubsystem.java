package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class IntakeSubsystem implements Subsystem, Supplier<Double>, Loggable {
  public static class IntakeConstant {
    public static double INTAKE_IN_SPEED = -0.2;
    public static double INTAKE_OUT_SPEED = 0.825;
    public static double INTAKE_STOP_SPEED = 0;
    public static double DETECTION_THRESHOLD = 2.0;
  }

  public EncodedMotor<DcMotorEx> motor;

  public RangeSensor rangeSensor;

  @Log.Number(name = "Bucket speed")
  public double bucketSensor = 0.0;

  public IntakeSubsystem(EncodedMotor<DcMotorEx> m, RangeSensor r) {
    motor = m;
    motor.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    rangeSensor = r;
  }

  public void in() {
    motor.setSpeed(IntakeConstant.INTAKE_IN_SPEED);
  }

  public void out() {
    motor.setSpeed(IntakeConstant.INTAKE_OUT_SPEED);
  }

  public void stop() {
    motor.setSpeed(IntakeConstant.INTAKE_STOP_SPEED);
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

  private int loopNum = 0;
  @Override
  public void periodic() {
    loopNum++;
    if (loopNum >=5) {
      bucketSensor = getSensorDistance();
      loopNum = 0;
    }
  }
}
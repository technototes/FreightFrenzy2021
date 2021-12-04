package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class IntakeSubsystem implements Subsystem, Supplier<Double>, Loggable {
  public static class IntakeConstant {
    public static double INTAKE_IN_SPEED = -0.8;
    public static double INTAKE_OUT_SPEED = 0.8;
    public static double INTAKE_STOP_SPEED = 0;
    public static double DETECTION_DISTANCE = 0.1; //needs to be tested
  }
  public EncodedMotor<DcMotorEx> motor;

  @Log.Number (name = "Bucket range")
  public RangeSensor rangeSensor;

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

  public double getSensorDistance() {
    return rangeSensor.getSensorValue();
  }

  public boolean isNearTarget() {
    return getSensorDistance() < IntakeConstant.DETECTION_DISTANCE;
  }
  @Override
  public Double get() {
    return motor.getSpeed();
  }
}

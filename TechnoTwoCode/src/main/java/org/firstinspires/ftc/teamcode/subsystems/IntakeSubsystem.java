package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.INTAKE_IN_SPEED;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class IntakeSubsystem implements Subsystem, Supplier<Double> {


  @Config
  public static class IntakeConstants{
    public static double INTAKE_IN_SPEED = 1;
    public static double INTAKE_OUT_SPEED = -1;
    public static double INTAKE_STOP_SPEED = 0;
    public static double DETECTION_DISTANCE = 5;
  }
  public Motor<DcMotorEx> motor;
  public RangeSensor rangeSensor;

  public IntakeSubsystem(Motor<DcMotorEx> m, RangeSensor rs) {
    motor = m;
    rangeSensor = rs;
  }

  public void in() {
    motor.setSpeed(INTAKE_IN_SPEED);
  }
  public void out() {
    motor.setSpeed(IntakeConstants.INTAKE_OUT_SPEED);
  }
  public void stop() {
    motor.setSpeed(IntakeConstants.INTAKE_STOP_SPEED);
  }
  public double getSensorDistance() {
    return rangeSensor.getSensorValue();
  }
  public boolean isNearTarget() {
    return getSensorDistance() < IntakeConstants.DETECTION_DISTANCE;
  }
  @Override

  public Double get() {
    return motor.getSpeed();
  }

}

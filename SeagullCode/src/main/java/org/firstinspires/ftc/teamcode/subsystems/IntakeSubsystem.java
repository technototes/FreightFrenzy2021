package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class IntakeSubsystem implements Subsystem, Supplier<Double> {
  public static class IntakeConstant {
    public static double INTAKE_IN_SPEED = -0.825;
    public static double INTAKE_OUT_SPEED = 0.825;
    public static double INTAKE_STOP_SPEED = 0;
  }
  public EncodedMotor<DcMotorEx> motor;

  public IntakeSubsystem(EncodedMotor<DcMotorEx> m) {
    motor = m;
    motor.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

  @Override
  public Double get() {
    return motor.getSpeed();
  }
}

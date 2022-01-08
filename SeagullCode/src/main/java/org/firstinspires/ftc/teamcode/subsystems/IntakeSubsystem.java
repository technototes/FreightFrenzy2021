package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class IntakeSubsystem implements Subsystem, Supplier<Double>, Loggable {
  public static class IntakeConstant {
    public static double INTAKE_IN_SPEED = -0.85;
    public static double INTAKE_OUT_SPEED = 1.0;
    public static double INTAKE_STOP_SPEED = 0;
    public static double DETECTION_THRESHOLD = 3.0;
  }

  public enum State {
    STOP,
    IN,
    OUT
  }

  private final EncodedMotor<DcMotorEx> motor;

  private final Rev2MDistanceSensor rangeSensor;

  @Log.Number (name = "Bucket sensor")
  public double bucketDistance;

  //TODO i dont like having gamepads in subsystems
  private CommandGamepad gamepad;

  private State currentState = State.STOP;

  public IntakeSubsystem(EncodedMotor<DcMotorEx> m, Rev2MDistanceSensor r) {
    motor = m;
    motor.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor.setPIDFCoeffecients(20,0,0,0);
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
    final double dist = rangeSensor.getDistance();
    bucketDistance = dist;
    return dist;
  }

  // Want:
  // startDetection, which starts reading the distance sensor every N cycles (the sensor is slow to read)
  // stopDetection, which stops reading the distance sensor
  // getSensorDistance returns the average over the past 3 sensor reads, to smooth out the noise
  // hasCargo uses the smoothed distance value
  public boolean hasCargo() {
    if (getSensorDistance() < IntakeConstant.DETECTION_THRESHOLD){
      if (gamepad != null){
        gamepad.rumble(1000);
      }
      return true;
    }
    return false;
  }

  public void setGamepad(CommandGamepad g){
    this.gamepad = g;
  }

  @Override
  public Double get() {
    return motor.getSpeed();
  }

  public State getState() {
    return currentState;
  }
}
package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.subsystem.Subsystem;

import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.*;

public class IntakeSubsystem implements Subsystem {
  @Config
  public static class IntakeConstants{
    public static double INTAKE_IN_SPEED = 1;
    public static double INTAKE_OUT_SPEED = -1;
    public static double INTAKE_STOP_SPEED = 0;
    public static double ACCEPTABLE_DISTANCE = 5;
  }
  public Motor<DcMotorEx> motor;


  public IntakeSubsystem(Motor<DcMotorEx> m) {
    motor = m;
  }
  public void in() {
    motor.setSpeed(INTAKE_IN_SPEED);
  }

}

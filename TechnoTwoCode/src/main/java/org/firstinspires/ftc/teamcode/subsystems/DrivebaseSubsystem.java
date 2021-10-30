package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.subsystem.Subsystem;

public class DrivebaseSubsystem implements Subsystem {
  public DrivebaseSubsystem(EncodedMotor<DcMotorEx> fl, EncodedMotor<DcMotorEx> fr,
                            EncodedMotor<DcMotorEx> rl, EncodedMotor<DcMotorEx> rr,
                            IMU i) {}

}

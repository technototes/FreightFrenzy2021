package org.firstinspires.ftc.samplecode.finch.subsystems;

import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

public class DrivebaseSubsystem implements Subsystem {
    public Motor leftMotor, rightMotor;
    public DrivebaseSubsystem(Motor l, Motor r){
        leftMotor = l;
        rightMotor = r;
    }
    public void forward(){
        leftMotor.setSpeed(1);
        rightMotor.setSpeed(1);
    }
    public void tankDrive(double x, double y){
        leftMotor.setSpeed(x+y);
        rightMotor.setSpeed(x-y);
    }
}

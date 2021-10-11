package org.firstinspires.ftc.samplecode.clawbot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.servo.Servo;

//class for hardware
public class Hardware {
    //drive motors
    public Motor<DcMotorEx> leftMotor, rightMotor;

    //claw arm
    public EncodedMotor<DcMotorEx> armMotor;

    //claw
    public Servo clawServo;

    //create all devices with correct names in hardware map
    public Hardware(){
        leftMotor = new Motor<>("left");
        rightMotor = new Motor<DcMotorEx>("right").setInverted(true);

        armMotor = new EncodedMotor<>("arm");

        clawServo = new Servo("claw");
    }
}

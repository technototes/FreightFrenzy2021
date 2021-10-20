package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;

public class Hardware {
    public static EncodedMotor<DcMotorEx> liftMotor;
    public static Servo leftDepositServo;
    public static Servo rightDepositServo;

    static {
        liftMotor = new EncodedMotor<DcMotorEx>("lift");
        leftDepositServo = new Servo("leftDeposit");
        rightDepositServo = new Servo("rightDeposit");
    }
}


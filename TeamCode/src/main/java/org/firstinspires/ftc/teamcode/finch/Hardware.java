package org.firstinspires.ftc.teamcode.finch;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.motor.MotorGroup;
import com.technototes.library.hardware.servo.Servo;

public class Hardware {
    public static Motor<DcMotorEx> left1, left2, right1, right2;
    public static MotorGroup<Motor<DcMotorEx>> left, right;
    public static Servo arm;
    static {
        left1 = new Motor<>("lMotor");
        left2 = new Motor<>("lMotor2");
        right1 = new Motor<>("rMotor");
        right1.invert();
        right2 = new Motor<>("rMotor2");
        right2.invert();
        left = new MotorGroup<>(left1, left2);
        right = new MotorGroup<>(right1, right2);
        arm = new Servo("tilt");



    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

public class Hardware {
    public static EncodedMotor<DcMotorEx> liftMotor;

    public static Servo leftDepositServo;
    public static Servo rightDepositServo;

    public static EncodedMotor<DcMotorEx> flDriveMotor;
    public static EncodedMotor<DcMotorEx> frDriveMotor;
    public static EncodedMotor<DcMotorEx> rlDriveMotor;
    public static EncodedMotor<DcMotorEx> rrDriveMotor;
    public static IMU imu;

    static {
        if(Robot.LIFT_CONNECTED) {
            liftMotor = new EncodedMotor<DcMotorEx>("lift");
        }
        if(Robot.DEPOSIT_CONNECTED) {
            leftDepositServo = new Servo("leftDeposit");
            rightDepositServo = new Servo("rightDeposit");
        }
        if(Robot.DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>("flMotor");
            frDriveMotor = new EncodedMotor<>("frMotor");
            rlDriveMotor = new EncodedMotor<>("rlMotor");
            rrDriveMotor = new EncodedMotor<>("rrMotor");
            imu = new IMU("imu");
        }
    }
}


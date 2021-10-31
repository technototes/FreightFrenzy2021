package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Hardware {
    public static EncodedMotor<DcMotorEx> liftMotor;

    public static Servo dumpServo;
    public static Servo armServo;

    public static EncodedMotor<DcMotorEx> flDriveMotor;
    public static EncodedMotor<DcMotorEx> frDriveMotor;
    public static EncodedMotor<DcMotorEx> rlDriveMotor;
    public static EncodedMotor<DcMotorEx> rrDriveMotor;
    public static IMU imu;

    public static Motor<DcMotorEx> intakeMotor;

    public static Motor<DcMotorEx> carouselMotor;

    public static Webcam camera;





    static {
        if(LIFT_CONNECTED) {
            liftMotor = new EncodedMotor<>("lift");
        }
        if(DEPOSIT_CONNECTED) {
            dumpServo = new Servo("dump").setRange(0, 0.6);
            armServo = new Servo("arm").invert();
        }
        if(DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>("flMotor");
            frDriveMotor = new EncodedMotor<>("frMotor");
            rlDriveMotor = new EncodedMotor<>("rlMotor");
            rrDriveMotor = new EncodedMotor<>("rrMotor");
            imu = new IMU("imu").remapAxes(AxesOrder.XYZ, IMU.AxesSigns.NNN);
        }
        if(CAROUSEL_CONNECTED){
            carouselMotor = new Motor<>("carousel");
        }
        if(VISION_CONNECTED){
            camera = new Webcam("webcam1");
        }
        if(INTAKE_CONNECTED){
            intakeMotor = new Motor<>("lift");
        }
    }
}


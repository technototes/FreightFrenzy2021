package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DEPOSIT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.LIFT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.VISION_CONNECTED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

public class Hardware {
    public static EncodedMotor<DcMotorEx> liftMotor;

    public static Servo DumpServo;
    //public static Servo rightDepositServo;

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
            //leftDepositServo = new Servo("leftDeposit").invert();

            DumpServo = new Servo("dumpServo");
        }
        if (DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>("flMotor");
            frDriveMotor = new EncodedMotor<>("frMotor");
            rlDriveMotor = new EncodedMotor<>("rlMotor");
            rrDriveMotor = new EncodedMotor<>("rrMotor");
            imu = new IMU("imu").remapAxes(AxesOrder.XYZ, IMU.AxesSigns.NNN);
        }
        if(CAROUSEL_CONNECTED) {
            carouselMotor = new EncodedMotor<>("carouselMotor");
        }
        if(VISION_CONNECTED) {
            camera = new Webcam("webcam1");
        }
        if(INTAKE_CONNECTED) {
            intakeMotor = new Motor<>("intake");
        }

    }
}
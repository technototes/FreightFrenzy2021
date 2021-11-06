package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAROUSEL_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DEPOSIT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.DRIVE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.INTAKE_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.LIFT_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.VISION_CONNECTED;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.CAP_CONNECTED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.hardware.servo.Servo;

import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;


import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.LIFT;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.CAMERA;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.CAP;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.CAROUSEL;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.DUMP;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.FL_MOTOR;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.FR_MOTOR;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.INTAKE;

import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.RL_MOTOR;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.RR_MOTOR;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;


public class Hardware {
    @Config
    public static class HardwareConstants{
        public static String LIFT = "lift";

        public static String DUMP = "dump";

        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";

        public static String CAROUSEL = "carousel";

        public static String CAMERA = "webcam1";

        public static String INTAKE = "intake";

        public static String CAP = "cap";
    }

    public static EncodedMotor<DcMotorEx> liftMotor;


    public static Servo armServo;

    public static EncodedMotor<DcMotorEx> flDriveMotor;
    public static EncodedMotor<DcMotorEx> frDriveMotor;
    public static EncodedMotor<DcMotorEx> rlDriveMotor;
    public static EncodedMotor<DcMotorEx> rrDriveMotor;
    public static IMU imu;

    public static Motor<DcMotorEx> intakeMotor;

    public static Motor<DcMotorEx> carouselMotor;

    public static Webcam camera;

    public static EncodedMotor<DcMotorEx> armMotor;

    public static RangeSensor intakeDistSensor;

    public static Servo capServo;

    public static Servo dumpServo;

    static {
        if(LIFT_CONNECTED) {
            liftMotor = new EncodedMotor<>(LIFT);
        }
        if(DEPOSIT_CONNECTED) {

            armServo = new Servo(LIFT).invert();
        }
        if(DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN);
        }
        if(CAROUSEL_CONNECTED){
            carouselMotor = new Motor<>(CAROUSEL);
        }
        if(VISION_CONNECTED){
            camera = new Webcam(CAMERA);
        }
        if(INTAKE_CONNECTED){
            intakeMotor = new Motor<>(INTAKE);
                    }

        if(ARM_CONNECTED) {
            armMotor = new EncodedMotor<>(LIFT);
        }
        if(CAP_CONNECTED){
            capServo = new Servo(CAP);
        }
        if (DEPOSIT_CONNECTED){
            dumpServo = new Servo(DUMP);
        }
    }
}
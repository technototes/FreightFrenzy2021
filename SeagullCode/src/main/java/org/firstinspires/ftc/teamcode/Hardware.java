package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.IMU.AxesSigns;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Hardware {
    @Config
    public static class HardwareConstants {
        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";
        public static String BUCKET_RANGE = "bucket_sensor";

        public static String CAROUSEL = "carousel";

        public static String CAMERA = "webcam1";

        public static String INTAKE = "intake";

        public static String BUCKET = "bucket";
        public static String ARM = "arm";

        public static String FRONT_RANGE = "front_range";
        public static String LEFT_RANGE = "left_range";
        public static String RIGHT_RANGE = "right_range";
    }

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;
    public Rev2MDistanceSensor bucketDistanceSensor;

    public EncodedMotor<DcMotorEx> intakeMotor;

    public Motor<DcMotorEx> carouselMotor;

    public Webcam camera;

    public Servo bucketServo;
    public EncodedMotor<DcMotorEx> bucketMotor;

    public Rev2MDistanceSensor frontDistanceSensor;
//    public Rev2MDistanceSensor leftDistanceSensor;
//    public Rev2MDistanceSensor rightDistanceSensor;

    public Hardware() {
        if (DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, AxesSigns.NNN);
            frontDistanceSensor = new Rev2MDistanceSensor(FRONT_RANGE).onUnit(DistanceUnit.INCH);
//            leftDistanceSensor = new Rev2MDistanceSensor(LEFT_RANGE).onUnit(DistanceUnit.INCH);
//            rightDistanceSensor = new Rev2MDistanceSensor(RIGHT_RANGE).onUnit(DistanceUnit.INCH);
        }
        if (CAROUSEL_CONNECTED) {
            carouselMotor = new Motor<>(CAROUSEL);
        }
        if (VISION_CONNECTED) {
            camera = new Webcam(CAMERA);
        }
        if (INTAKE_CONNECTED) {
            intakeMotor = new EncodedMotor<>(INTAKE);
        }

        if (DUMP_CONNECTED) {
            bucketServo = new Servo(BUCKET);
            bucketMotor = new EncodedMotor<>(ARM);
            bucketDistanceSensor = new Rev2MDistanceSensor(BUCKET_RANGE).onUnit(DistanceUnit.INCH);
        }
    }
}
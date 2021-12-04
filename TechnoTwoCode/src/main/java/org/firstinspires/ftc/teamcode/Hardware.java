package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.IMU.AxesSigns;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Hardware {
    @Config
    public static class HardwareConstants {
        public static String LIFT = "lift";

        public static String DUMP = "dump";

        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";
        public static String BUCKET_RANGE = "bsensor";

        public static String CAROUSEL = "carousel";

        public static String CAMERA = "webcam1";

        public static String INTAKE = "intake";

        public static String CAP = "cap";

        public static String BUCKET = "bucket";
        public static String ARM = "arm";
    }

    public EncodedMotor<DcMotorEx> liftMotor;


    public Servo armServo;

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;
    public RangeSensor bucketRangeSensor;

    public Motor<DcMotorEx> intakeMotor;

    public Motor<DcMotorEx> carouselMotor;

    public Webcam camera;

    public Servo capServo;

    public Servo dumpServo;

    public Servo bucketServo;
    public EncodedMotor<DcMotorEx> bucketMotor;

    public Hardware() {
        if (DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, AxesSigns.NNN);
        }
        if (CAROUSEL_CONNECTED) {
            carouselMotor = new Motor<>(CAROUSEL);
        }
        if (VISION_CONNECTED) {
            camera = new Webcam(CAMERA);
        }
        if (INTAKE_CONNECTED) {
            intakeMotor = new Motor<>(INTAKE);
        }

        if (CAP_CONNECTED) {
            capServo = new Servo(CAP);
        }

        if (DUMP_CONNECTED) {
            bucketServo = new Servo(BUCKET);
            bucketMotor = new EncodedMotor<DcMotorEx>(ARM).invert();
            bucketRangeSensor = new RangeSensor(BUCKET_RANGE).setDistanceUnit(DistanceUnit.INCH);
        }
    }
}
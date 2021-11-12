package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.IMU.AxesSigns;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.hardware.servo.ServoGroup;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.*;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Hardware {
    @Config
    public static class HardwareConstants{
        public static String LIFT = "lift";

        public static String L_DUMP = "ldump";
        public static String R_DUMP = "rdump";

        public static String ARM = "arm";

        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";
        public static String L_RANGE = "ldistance";
        public static String R_RANGE = "rdistance";


        public static String CAROUSEL = "carousel";

        public static String CAMERA = "webcam";

        public static String INTAKE = "intake";
        public static String INTAKE_RANGE = "irange";

        public static String CAP = "cap";

    }

    public EncodedMotor<DcMotorEx> liftMotor;

    public Servo dumpLeftServo, dumpRightServo;
    public ServoGroup dumpServos;
    public Servo armServo;

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;
    public RangeSensor leftRangeSensor;
    public RangeSensor rightRangeSensor;


    public Motor<DcMotorEx> intakeMotor;

    public Motor<DcMotorEx> carouselMotor;

    public RangeSensor intakeDistSensor;

    public Servo capServo;

    public Webcam camera;

    public Hardware() {
        if(LIFT_CONNECTED) {
            liftMotor = new EncodedMotor<>(LIFT);
        }
        if(DEPOSIT_CONNECTED) {
            dumpLeftServo = new Servo(L_DUMP).invert();
            dumpRightServo = new Servo(R_DUMP);
            dumpServos = new ServoGroup(dumpLeftServo, dumpRightServo).setStartingPosition(DepositSubsystem.DepositConstants.CARRY);
            armServo = new Servo(ARM);
        }
        if(DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, AxesSigns.NNN);
            leftRangeSensor = new RangeSensor(L_RANGE).setDistanceUnit(DistanceUnit.INCH);
            rightRangeSensor = new RangeSensor(R_RANGE).setDistanceUnit(DistanceUnit.INCH);
        }
        if(CAROUSEL_CONNECTED){
            carouselMotor = new Motor<>(CAROUSEL);
        }
        if(VISION_CONNECTED){
            camera = new Webcam(CAMERA);
        }
        if(INTAKE_CONNECTED){
            intakeMotor = new Motor<>(INTAKE);
            intakeDistSensor = new RangeSensor(INTAKE_RANGE).setDistanceUnit(DistanceUnit.INCH);
        }
        if(CAP_CONNECTED){
            capServo = new Servo(CAP);
        }
    }
}


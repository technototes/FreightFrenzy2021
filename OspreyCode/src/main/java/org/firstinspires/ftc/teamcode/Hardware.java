package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.IMU.AxesSigns;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.*;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.*;

public class Hardware implements Loggable {
    @Config
    public static class HardwareConstants{
        public static String LIFT = "lift";

        public static String DUMP = "dump";
        public static String ARM = "arm";

        public static String SLIDE = "slide";
        public static String TURRET = "turret";

        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";
        public static String L_RANGE = "ldistance";
        public static String R_RANGE = "rdistance";
        public static String F_RANGE = "fdistance";



        public static String CAROUSEL = "carousel";

        public static String CAMERA = "webcam";

        public static String INTAKE = "intake";

        public static String CAP = "cap";

    }

    public EncodedMotor<DcMotorEx> liftMotor;

    public Servo dumpServo;
    public Servo armServo;

    public Servo turretServo;
    public Servo slideServo;

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;
    public RangeSensor leftRangeSensor;
    public RangeSensor rightRangeSensor;
    public RangeSensor frontRangeSensor;
    public Motor<DcMotorEx> intakeMotor;

    public Motor<DcMotorEx> carouselMotor;

    public Servo capServo;

    public Webcam camera;

    public Hardware() {
        if(LIFT_CONNECTED) {
            liftMotor = new EncodedMotor<>(LIFT);
        }
        if(DEPOSIT_CONNECTED) {
            dumpServo = new Servo(DUMP).invert().setStartingPosition(ArmSubsystem.ArmConstants.CARRY);
            armServo = new Servo(ARM).setStartingPosition(ArmSubsystem.ArmConstants.UP);
        }
        if(EXTENSION_CONNECTED){
            slideServo = new Servo(SLIDE).setStartingPosition(ExtensionSubsystem.ExtensionConstants.IN);
            turretServo = new Servo(TURRET).setStartingPosition(ExtensionSubsystem.ExtensionConstants.CENTER);
        }
        if(DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, AxesSigns.NPP);
            leftRangeSensor = new RangeSensor(L_RANGE).setDistanceUnit(DistanceUnit.INCH);
            rightRangeSensor = new RangeSensor(R_RANGE).setDistanceUnit(DistanceUnit.INCH);
            frontRangeSensor = new RangeSensor(F_RANGE).setDistanceUnit(DistanceUnit.INCH);
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
        if(CAP_CONNECTED){
            capServo = new Servo(CAP).setStartingPosition(CapSubsystem.CapConstants.COLLECT );
        }
    }
}


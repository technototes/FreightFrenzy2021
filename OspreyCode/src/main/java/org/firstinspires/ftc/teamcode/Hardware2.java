package org.firstinspires.ftc.teamcode;


import static com.technototes.library.hardware2.HardwareBuilder.*;
import static org.firstinspires.ftc.teamcode.Hardware2.Hardware2Constants.*;

import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAP_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.CAROUSEL_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DEPOSIT_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.DRIVE_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.EXTENSION_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.INTAKE_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.LIFT_ENABLED;
import static org.firstinspires.ftc.teamcode.Robot.SubsystemConstants.VISION_ENABLED;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.technototes.library.hardware2.IMUBuilder;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;


public class Hardware2 {
    @com.acmerobotics.dashboard.config.Config
    public static class Hardware2Constants{
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
        public static String INTAKE_COLOR = "irange";

        public static String CAP = "cap";

    }


    public DcMotorEx liftMotor;

    public Servo dumpServo;
    public Servo armServo;

    public Servo turretServo;
    public Servo slideServo;

    public DcMotorEx flDriveMotor;
    public DcMotorEx frDriveMotor;
    public DcMotorEx rlDriveMotor;
    public DcMotorEx rrDriveMotor;
    public BNO055IMU imu;
    public DistanceSensor leftRangeSensor;
    public DistanceSensor rightRangeSensor;
    public DistanceSensor frontRangeSensor;

    public DcMotorEx intakeMotor;
    public ColorRangeSensor intakeSensor;

    public DcMotorEx carouselMotor;

    public Servo capServo;

    public Webcam camera;

    public Hardware2() {
        if(LIFT_ENABLED) {
            liftMotor = motor(LIFT).brake().tare().build();
        }
        if(DEPOSIT_ENABLED) {
            dumpServo = servo(DUMP).reverse().at(ArmSubsystem.ArmConstants.CARRY).build();
            armServo = servo(ARM).at(ArmSubsystem.ArmConstants.UP).build();
        }
        if(EXTENSION_ENABLED){
            slideServo = servo(SLIDE).at(ExtensionSubsystem.ExtensionConstants.IN).build();
            turretServo = servo(TURRET).at(ExtensionSubsystem.ExtensionConstants.CENTER).expandedPWM().build();
        }
        if(DRIVE_ENABLED) {
            flDriveMotor = motor(FL_MOTOR).build();
            frDriveMotor = motor(FR_MOTOR).build();
            rlDriveMotor = motor(RL_MOTOR).build();
            rrDriveMotor = motor(RR_MOTOR).build();
            imu = imu(Hardware.HardwareConstants.IMU).remap(AxesOrder.YXZ, IMUBuilder.AxesSigns.NPP).build();
            leftRangeSensor = distance(L_RANGE).build();
            rightRangeSensor = distance(R_RANGE).build();
            frontRangeSensor = distance(F_RANGE).build();
        }
        if(CAROUSEL_ENABLED){
            carouselMotor = motor(CAROUSEL).brake().build();
        }
        if(VISION_ENABLED){
            camera = new Webcam(CAMERA);
        }
        if(INTAKE_ENABLED){
            intakeMotor = motor(INTAKE).build();
            intakeSensor = colorRange(INTAKE_COLOR).build();
        }
        if(CAP_ENABLED){
            capServo = servo(CAP).at(CapSubsystem.CapConstants.COLLECT).build() ;
        }
    }
}

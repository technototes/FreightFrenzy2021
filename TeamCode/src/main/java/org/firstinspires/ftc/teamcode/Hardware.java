package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.HardwareDevice;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.EncodedMotorGroup;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.motor.MotorGroup;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.roadrunnercode.util.Encoder;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import com.technototes.library.hardware.sensor.IMU;

/** Class for the hardware devices of the robot
 *
 */
public class Hardware implements Loggable {

    //drivebase
    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;

    public Encoder leftOdometryEncoder;
    public Encoder rightOdometryEncoder;
    public Encoder frontOdometryEncoder;

    public IMU imu;

    //index
    public Servo indexArmServo;
    public Servo indexPivotServo;

    //intake
    public Motor intakeMotor1;
    public Motor intakeMotor2;
    public MotorGroup intakeMotorGroup;

    //shooter
    public EncodedMotor<DcMotorEx> shooterMotor1;
    public EncodedMotor<DcMotorEx> shooterMotor2;
    public EncodedMotorGroup shooterMotorGroup;

    public Servo shooterFlapServo;
    public Servo shooterAngleServo;


    //wobble
    public Servo wobbleArmServo;
    public Servo wobbleClawServo;

    public OpenCvCamera webcam;

    public Hardware(){
        flDriveMotor = new EncodedMotor<>("flMotor");
        frDriveMotor = new EncodedMotor<>("frMotor");
        rlDriveMotor = new EncodedMotor<>("rlMotor");
        rrDriveMotor = new EncodedMotor<>("rrMotor");

        leftOdometryEncoder = new Encoder("shooter2");
        rightOdometryEncoder = new Encoder("intake2");
        frontOdometryEncoder = new Encoder("intake1");

        imu = new IMU("imu");

        indexArmServo = new Servo("indexarm");
        indexPivotServo = new Servo("indexpivot");

        intakeMotor1 = new Motor<>("intake1");
        intakeMotor2 = new Motor<>("intake2");
        //TODO fix this warning
        intakeMotorGroup = new MotorGroup(intakeMotor1, intakeMotor2);

        shooterMotor1 = new EncodedMotor<DcMotorEx>("shooter1").invert();
        shooterMotor2 = new EncodedMotor<DcMotorEx>("shooter2").invert();
        shooterMotorGroup = new EncodedMotorGroup(shooterMotor1, shooterMotor2);

        shooterFlapServo = new Servo("flapservo");
        shooterAngleServo = new Servo("wobblearm");

        wobbleArmServo = new Servo("wobblearm");
        wobbleClawServo = new Servo("wobbleclaw");

        webcam = OpenCvCameraFactory.getInstance().createWebcam(HardwareDevice.hardwareMap.get(WebcamName.class, "webcam"),
                HardwareDevice.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                        HardwareDevice.hardwareMap.appContext.getPackageName()));
    }

}

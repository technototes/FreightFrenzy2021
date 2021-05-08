package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.HardwareDevice;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.EncodedMotorGroup;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.motor.MotorGroup;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.hardware.servo.ServoGroup;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.roadrunnercode.util.AxesSigns;
import org.firstinspires.ftc.teamcode.roadrunnercode.util.BNO055IMUUtil;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.roadrunnercode.util.Encoder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
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

    //intake
    public Motor intakeMotor1;
    public Motor intakeMotor2;
    public MotorGroup intakeMotorGroup;

    //shooter
    public EncodedMotor<DcMotorEx> shooterMotor1;
    public EncodedMotor<DcMotorEx> shooterMotor2;
    public EncodedMotorGroup shooterMotorGroup;

    public Servo shooterFlapServo;


    //wobble
    public Servo wobbleLeftArmServo;
    public Servo wobbleRightArmServo;
    public ServoGroup wobbleArmServos;

    public Servo wobbleClawServo;
    public Servo wobbleTurretServo;


    public OpenCvCamera webcam;
    public Servo turretServo;
    public Servo raiseServo;

    public Servo leftStick;
    public Servo rightStick;
    public ServoGroup sticks;

    public Hardware(){
        flDriveMotor = new EncodedMotor<>("flMotor");
        frDriveMotor = new EncodedMotor<>("frMotor");
        rlDriveMotor = new EncodedMotor<>("rlMotor");
        rrDriveMotor = new EncodedMotor<>("rrMotor");

        leftOdometryEncoder = new Encoder("flMotor").invert();
        rightOdometryEncoder = new Encoder("shooter2").invert();
        frontOdometryEncoder = new Encoder("rlMotor");

        imu = new IMU("imu");
        BNO055IMUUtil.remapAxes(imu.device, AxesOrder.XYZ, AxesSigns.NNN);

        indexArmServo = new Servo("indexarm");

        intakeMotor1 = new Motor<>("intake1");
        intakeMotor2 = new Motor<>("intake2").invert();
        //TODO fix this warning
        intakeMotorGroup = new MotorGroup(intakeMotor1, intakeMotor2);

        shooterMotor1 = new EncodedMotor<DcMotorEx>("shooter1").invert();
        shooterMotor2 = new EncodedMotor<DcMotorEx>("shooter2").invert();
        shooterMotorGroup = new EncodedMotorGroup(shooterMotor1, shooterMotor2);

        shooterFlapServo = new Servo("flapservo");

        wobbleLeftArmServo = new Servo("lwobblearm").setRange(0.2, 0.55);
        wobbleRightArmServo = new Servo("rwobblearm").setRange(0.45, 0.8).invert();
        wobbleArmServos = new ServoGroup(wobbleLeftArmServo, wobbleRightArmServo);

        wobbleClawServo = new Servo("wobbleclaw").setRange(0.1, 0.6);
        wobbleTurretServo = new Servo("wobbleturret").setRange(0.5, 1);

        turretServo = new Servo("turret").setRange(0, 1);
        raiseServo = new Servo("raise");

        webcam = OpenCvCameraFactory.getInstance().createWebcam(HardwareDevice.hardwareMap.get(WebcamName.class, "webcam"),
                HardwareDevice.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                        HardwareDevice.hardwareMap.appContext.getPackageName()));

        leftStick = new Servo("lstick").setRange(0, 1).invert();
        rightStick = new Servo("rstick").setRange(0, 0.5);
        sticks = new ServoGroup(leftStick, rightStick);
    }

}

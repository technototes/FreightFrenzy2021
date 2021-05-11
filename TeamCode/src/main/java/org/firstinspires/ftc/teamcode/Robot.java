package org.firstinspires.ftc.teamcode;

import com.technototes.library.hardware.HardwareDevice;
import com.technototes.logger.Color;
import com.technototes.logger.Log;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.opmodes.WobblesThenStartingRings;
import org.firstinspires.ftc.teamcode.subsystems.OdometrySubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.StickSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionStackSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.openftc.easyopencv.OpenCvCameraRotation;

/** Class for the subsystems on the robot
 *
 */
public class Robot implements Loggable {



    public Hardware hardware;


    //drivebase
    public DrivebaseSubsystem drivebaseSubsystem;

    public OdometrySubsystem odometrySubsystem;

    //index
    @Log(name = "Index", index = 4, color = Color.ORANGE, entryColor = Color.LIGHT_GRAY)
    public IndexSubsystem indexSubsystem;

    //intake not logged with autonomous opmode
    @LogConfig.Blacklist(WobblesThenStartingRings.class)
    @Log(name = "Intake", index = 1, color = Color.BLUE, entryColor = Color.LIGHT_GRAY)
    public IntakeSubsystem intakeSubsystem;

    //shooter logged with number/progress bar
    //@Log.NumberBar(name = "Shooter", index =  2, completeBarColor = Color.GREEN, min = 0, max=2000)
    public ShooterSubsystem shooterSubsystem;

    //wobble
    @Log(name = "Wobble", index = 3, color = Color.RED, entryColor = Color.LIGHT_GRAY)
    public WobbleSubsystem wobbleSubsystem;

    //numrings is only shown during the init period of the match
    @LogConfig.Run(duringInit = true, duringRun = false)
    @Log.Number(name = "numrings", index = 0, color = Color.YELLOW, numberColor = Color.YELLOW)
    public VisionStackSubsystem visionStackSubsystem;

    public VisionAimSubsystem visionAimSubsystem;
    public TurretSubsystem turretSubsystem;

    @Log(name="Sticks", index = 5)
    public StickSubsystem stickSubsystem;

    //voltage displayed in yellow to catch driver's eye
    @Log.Number(name="VOLTAGE", index = 0, color = Color.YELLOW, numberColor = Color.LIGHT_GRAY)
    public double getVoltage(){
        double d = HardwareDevice.hardwareMap.voltageSensor.iterator().next().getVoltage();
        System.out.println(d);
        return d;
    }

    public Robot(boolean stack){
        hardware = new Hardware();

        odometrySubsystem = new OdometrySubsystem(hardware.leftOdometryEncoder, hardware.rightOdometryEncoder, hardware.frontOdometryEncoder);

        drivebaseSubsystem = new DrivebaseSubsystem(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor, hardware.imu, odometrySubsystem);

        indexSubsystem = new IndexSubsystem(hardware.indexArmServo);

        intakeSubsystem = new IntakeSubsystem(hardware.intakeMotorGroup);

        shooterSubsystem = new ShooterSubsystem(hardware.shooterMotor1, hardware.shooterMotor2, hardware.shooterFlapServo);

        wobbleSubsystem =  new WobbleSubsystem(hardware.wobbleArmServos, hardware.wobbleClawServo, hardware.wobbleTurretServo);

        if(stack) {
            visionStackSubsystem = new VisionStackSubsystem(hardware.webcam);
        }else {
            visionAimSubsystem = new VisionAimSubsystem(hardware.webcam);
        }




        turretSubsystem = new TurretSubsystem(hardware.turretServo, hardware.raiseServo);

        stickSubsystem = new StickSubsystem(hardware.sticks);
    }
    public Robot(){
        this(false);
    }
}

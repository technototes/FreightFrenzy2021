package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class Robot {
    public static final boolean LIFT_CONNECTED = false;
    public static final boolean DEPOSIT_CONNECTED = true;
    public static final boolean DRIVE_CONNECTED = true;


    public LiftSubsystem liftSubsystem;

    public DepositSubsystem depositSubsystem;

    public DrivebaseSubsystem drivebaseSubsystem;

    public Robot(){
        if(LIFT_CONNECTED) liftSubsystem = new LiftSubsystem(Hardware.liftMotor);

        if(DEPOSIT_CONNECTED) depositSubsystem = new DepositSubsystem(Hardware.leftDepositServo, Hardware.rightDepositServo);

        if(DRIVE_CONNECTED) drivebaseSubsystem = new DrivebaseSubsystem(Hardware.flDriveMotor, Hardware.frDriveMotor, Hardware.rlDriveMotor, Hardware.rrDriveMotor, Hardware.imu);
    }
}

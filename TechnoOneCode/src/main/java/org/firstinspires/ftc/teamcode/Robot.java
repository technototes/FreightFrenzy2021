package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class Robot {
    public LiftSubsystem liftSubsystem;
    public DepositSubsystem depositSubsystem;
    public Robot(){
        liftSubsystem = new LiftSubsystem(Hardware.liftMotor);
        depositSubsystem = new DepositSubsystem(Hardware.leftDepositServo, Hardware.rightDepositServo);
    }
}

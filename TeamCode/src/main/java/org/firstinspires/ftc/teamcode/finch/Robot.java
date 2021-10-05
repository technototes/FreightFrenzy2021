package org.firstinspires.ftc.teamcode.finch;

import org.firstinspires.ftc.teamcode.finch.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.finch.subsystems.DrivebaseSubsystem;

public class Robot {
    public DrivebaseSubsystem drivebaseSubsystem;
    public ArmSubsystem armSubsystem;
    public Robot(){
        drivebaseSubsystem = new DrivebaseSubsystem(Hardware.left1, Hardware.right1);
        armSubsystem = new ArmSubsystem(Hardware.arm);
    }
}

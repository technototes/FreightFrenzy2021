package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.technototes.library.command.Command;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.util.MathUtils;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class VisionAlignCommand extends WaitCommand {
    public VisionSubsystem visionSubsystem;
    public DrivebaseSubsystem drivebaseSubsystem;
    public int target = 8;
    public double scale = 30;

    public VisionAlignCommand(DrivebaseSubsystem d, VisionSubsystem v){
        super(0.1);
        drivebaseSubsystem = d;
        visionSubsystem = v;
    }

    @Override
    public void init() {
        drivebaseSubsystem.setDrivePower(new Pose2d(0, 0, -(visionSubsystem.mean-target)/scale));
        System.out.println(visionSubsystem.mean);
    }
}

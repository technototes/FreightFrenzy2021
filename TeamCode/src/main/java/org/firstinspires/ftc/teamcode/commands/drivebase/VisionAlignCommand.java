package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionStackSubsystem;

public class VisionAlignCommand extends WaitCommand {
    public VisionStackSubsystem visionSubsystem;
    public DrivebaseSubsystem drivebaseSubsystem;
    public int target = 9;
    public double scale = 1;

    public VisionAlignCommand(DrivebaseSubsystem d, VisionStackSubsystem v){
        super(0);
        drivebaseSubsystem = d;
        visionSubsystem = v;
    }

    @Override
    public void init() {
//        drivebaseSubsystem.setDrivePower(new Pose2d(0, 0, -(visionSubsystem.mean-target)/scale));
        drivebaseSubsystem.setDrivePower(new Pose2d(0, 0, Math.pow(4*Math.PI/2-Angle.norm(drivebaseSubsystem.getExternalHeading()-Math.PI/1)-Math.PI/1, 1)/scale));
        //System.out.println(visionSubsystem.mean);
    }
}

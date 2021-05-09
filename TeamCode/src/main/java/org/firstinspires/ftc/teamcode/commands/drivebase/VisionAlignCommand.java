package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionStackSubsystem;

public class VisionAlignCommand extends Command {
    public  VisionAimSubsystem visionSubsystem;
    public TurretSubsystem turretSubsystem;
    public int target = 25;
    public static final double P = 0.0002;

    public VisionAlignCommand(TurretSubsystem t, VisionAimSubsystem v){
        turretSubsystem = t;
        visionSubsystem = v;
    }
    double cur;
    @Override
    public void init() {
        cur = visionSubsystem.getAvg();
    }

    @Override
    public void execute() {
        turretSubsystem.changeBy(Math.pow(Range.clip(-(cur-target), -5, 5), 3)*P);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(cur-target) <= 1;
    }
}

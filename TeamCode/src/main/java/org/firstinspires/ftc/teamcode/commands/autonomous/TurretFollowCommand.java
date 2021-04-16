package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;
import com.technototes.library.util.MathUtils;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class TurretFollowCommand extends Command {
    public ShooterSubsystem shooterSubsystem;
    public DrivebaseSubsystem drivebaseSubsystem;

    public TurretFollowCommand(DrivebaseSubsystem d, ShooterSubsystem s){
        drivebaseSubsystem = d;
        shooterSubsystem = s;
    }

    @Override
    public void init() {
        Pose2d p = new Pose2d(132, 0).minus(drivebaseSubsystem.getPoseEstimate());
        double a = Angle.norm(p.vec().angle()-drivebaseSubsystem.getExternalHeading()+Math.PI);
        //a = (a+Math.PI)%(2*Math.PI)-Math.PI;
        a-=Math.PI;
        System.out.println(a);
        shooterSubsystem.setAngle(MathUtils.map(a, -Math.PI/2, Math.PI/2, 1, 0));
    }
}

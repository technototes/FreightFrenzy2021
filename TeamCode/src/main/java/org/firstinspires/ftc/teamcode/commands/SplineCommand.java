package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class SplineCommand extends PathCommand {
    public double endTanganet;
    public Pose2d pose2d;
    public SplineCommand(DrivebaseSubsystem sub, double x, double y, double endTan, double endRot) {
        this(sub, new Pose2d(x,y,Math.toRadians(endRot)), endTan);
    }


    public SplineCommand(DrivebaseSubsystem sub, double x, double y, double endTan){
        this(sub, x, y, endTan, sub.getExternalHeading());
    }

    public SplineCommand(DrivebaseSubsystem sub, Pose2d pose, double endTan){
        super(sub);
        pose2d = pose;
        endTanganet = endTan;
    }

    @Override
    public void init() {
        subsystem.followTrajectoryAsync(subsystem.trajectoryBuilder(subsystem.getPoseEstimate(), subsystem.getExternalHeading())
                .splineToLinearHeading(pose2d, Math.toRadians(endTanganet))
                .build());
    }

}

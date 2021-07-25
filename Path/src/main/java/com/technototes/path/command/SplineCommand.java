package com.technototes.path.command;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


public class SplineCommand extends PathCommand {
    public double endTanganet;
    public Pose2d pose2d;
    public SplineCommand(MecanumDrivebaseSubsystem sub, double x, double y, double endTan, double endRot) {
        this(sub, new Pose2d(x,y,Math.toRadians(endRot)), endTan);
    }


    public SplineCommand(MecanumDrivebaseSubsystem sub, double x, double y, double endTan){
        this(sub, x, y, endTan, sub.getExternalHeading());
    }

    public SplineCommand(MecanumDrivebaseSubsystem sub, Pose2d pose, double endTan){
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

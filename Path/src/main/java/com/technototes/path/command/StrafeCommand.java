package com.technototes.path.command;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


public class StrafeCommand extends PathCommand {
    public Pose2d pose2d;
    public StrafeCommand(MecanumDrivebaseSubsystem sub, double x, double y, double endRot) {
        this(sub, new Pose2d(x,y,endRot));
    }


    public StrafeCommand(MecanumDrivebaseSubsystem sub, double x, double y){
        this(sub, x, y, Math.toDegrees(sub.getExternalHeading()));
    }

    public StrafeCommand(MecanumDrivebaseSubsystem sub, Pose2d pose){
        super(sub);
        pose2d = new Pose2d(pose.getX(), pose.getY(), Math.toRadians(pose.getHeading()));

    }

    @Override
    public void init() {
        subsystem.followTrajectoryAsync(subsystem.trajectoryBuilder(subsystem.getPoseEstimate())//, subsystem.getExternalHeading())
                .lineToLinearHeading(pose2d)
                .build());
    }

}

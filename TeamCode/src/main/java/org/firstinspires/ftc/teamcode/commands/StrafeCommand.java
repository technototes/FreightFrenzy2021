package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class StrafeCommand extends PathCommand {
    public Pose2d pose2d;
    public StrafeCommand(DrivebaseSubsystem sub, double x, double y, double endRot) {
        this(sub, new Pose2d(x,y,endRot));
    }


    public StrafeCommand(DrivebaseSubsystem sub, double x, double y){
        this(sub, x, y, Math.toDegrees(sub.getExternalHeading()));
    }

    public StrafeCommand(DrivebaseSubsystem sub, Pose2d pose){
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

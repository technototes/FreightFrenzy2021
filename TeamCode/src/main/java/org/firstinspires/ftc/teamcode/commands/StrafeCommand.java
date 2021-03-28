package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class StrafeCommand extends PathCommand {
    public double xpos, ypos;
    public double endRotation;
    public StrafeCommand(DrivebaseSubsystem sub, double x, double y, double endRot) {
        super(sub);
        xpos = x;
        ypos = y;
        endRotation = endRot;
    }


    public StrafeCommand(DrivebaseSubsystem sub, double x, double y){
        this(sub, x, y, 0);
    }

    @Override
    public void init() {
        subsystem.followTrajectoryAsync(subsystem.trajectoryBuilder(subsystem.getPoseEstimate(), subsystem.getExternalHeading())
                .lineToLinearHeading(new Pose2d(xpos, ypos, Math.toRadians(endRotation)))
                .build());
    }

}

package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class SplineCommand extends PathCommand {
    public double xpos, ypos, endTanganet;
    public double endRotation;
    public SplineCommand(DrivebaseSubsystem sub, double x, double y, double endTan, double endRot) {
        super(sub);
        xpos = x;
        ypos = y;
        endTanganet = endTan;
        endRotation = endRot;
    }


    public SplineCommand(DrivebaseSubsystem sub, double x, double y, double endTan){
        this(sub, x, y, endTan, sub.getExternalHeading());
    }

    @Override
    public void init() {
        subsystem.followTrajectoryAsync(subsystem.trajectoryBuilder(subsystem.getPoseEstimate(), subsystem.getExternalHeading())
                .splineToSplineHeading(new Pose2d(xpos,ypos, Math.toRadians(endRotation)), Math.toRadians(endTanganet))
                .build());
    }

}

package com.technototes.path.command;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;


public class NewNewTrajectoryCommand extends PathCommand {
    public TrajectorySequence trajectory;

    public NewNewTrajectoryCommand(MecanumDrivebaseSubsystem sub, TrajectorySequence t) {
        super(sub);
        trajectory = t;
    }
    @Override
    public void init() {
        subsystem.followTrajectorySequenceAsync(trajectory);
    }

}

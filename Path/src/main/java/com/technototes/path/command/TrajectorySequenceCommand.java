package com.technototes.path.command;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;


public class TrajectorySequenceCommand extends PathCommand {
    public TrajectorySequence trajectory;

    public TrajectorySequenceCommand(MecanumDrivebaseSubsystem sub, TrajectorySequence t) {
        super(sub);
        trajectory = t;
    }
    public TrajectorySequenceCommand(MecanumDrivebaseSubsystem sub, Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> t) {
        super(sub);
        trajectory = t.apply(sub::trajectorySequenceBuilder);
    }
    @Override
    public void init() {
        subsystem.followTrajectorySequenceAsync(trajectory);
    }

    @Override
    public void end(boolean cancel) {
        subsystem.followTrajectorySequenceAsync(null);
    }
}

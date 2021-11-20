package com.technototes.path.command;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.technototes.library.command.Command;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;


public class TrajectorySequenceCommand implements Command {
    public TrajectorySequence trajectory;
    public MecanumDrivebaseSubsystem subsystem;

    public TrajectorySequenceCommand(MecanumDrivebaseSubsystem sub, TrajectorySequence t) {
        subsystem = sub;
        trajectory = t;
    }
    public TrajectorySequenceCommand(MecanumDrivebaseSubsystem sub, Trajectory t) {
        subsystem = sub;
        trajectory = sub.trajectorySequenceBuilder(t.start()).addTrajectory(t).build();
    }
    public TrajectorySequenceCommand(MecanumDrivebaseSubsystem sub, Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> t) {
        subsystem = sub;
        trajectory = t.apply(sub::trajectorySequenceBuilder);
    }
    public <T> TrajectorySequenceCommand(MecanumDrivebaseSubsystem sub, BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, T, TrajectorySequence> t, T mux) {
        subsystem = sub;
        trajectory = t.apply(sub::trajectorySequenceBuilder, mux);
    }

    @Override
    public void initialize() {
        subsystem.followTrajectorySequenceAsync(trajectory);
    }

    @Override
    public void execute() {
        subsystem.update();
    }

    @Override
    public boolean isFinished() {
        return !subsystem.isBusy();
    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.stop();
    }
}

package com.technototes.path.command;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


public class NewTrajectoryCommand extends PathCommand {
    public Trajectory trajectory;

    public NewTrajectoryCommand(MecanumDrivebaseSubsystem sub, Trajectory t) {
        super(sub);
        trajectory = t;
    }
    @Override
    public void init() {
        subsystem.followTrajectoryAsync(trajectory);
    }

}

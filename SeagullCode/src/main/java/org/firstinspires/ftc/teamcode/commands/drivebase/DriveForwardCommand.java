package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class DriveForwardCommand implements Command {
    DrivebaseSubsystem drive;
    Pose2d currentPose;

    public DriveForwardCommand(DrivebaseSubsystem drive, Pose2d currentPose) {
        this.drive = drive;
        this.currentPose = currentPose;
    }

    @Override
    public void execute() {
        CommandScheduler.getInstance().schedule(
                new TrajectorySequenceCommand(drive, b -> b.apply(currentPose).forward(10).build())
        );
    }
}

package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class DriveToCenterCommandGroup extends SequentialCommandGroup {
    public DriveToCenterCommandGroup(DrivebaseSubsystem drive, Pose2d currentPose) {
        super(
                new TrajectorySequenceCommand(drive, b -> b.apply(currentPose).lineToLinearHeading(new Pose2d(45, 45, 0)).build())
        );
    }
}

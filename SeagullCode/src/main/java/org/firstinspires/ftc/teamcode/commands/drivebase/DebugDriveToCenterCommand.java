package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.technototes.library.command.Command;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.sequencesegment.SequenceSegment;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.text.MessageFormat;

public class DebugDriveToCenterCommand implements Command {
    private DrivebaseSubsystem drivebaseSubsystem;
    private Pose2d currentPose;
    private final Pose2d targetPose = new Pose2d(45, 45, 0);

    public DebugDriveToCenterCommand(DrivebaseSubsystem drive, Pose2d currentPose) {
        this.drivebaseSubsystem = drive;
        this.currentPose = currentPose;
        addRequirements(this.drivebaseSubsystem);
    }

    @Override
    public void execute() {
        TrajectorySequence generatedTrajectorySequence = drivebaseSubsystem.trajectorySequenceBuilder(currentPose).lineToLinearHeading(targetPose).build();
        for (int i = 0; i < generatedTrajectorySequence.size(); i++){
            SequenceSegment currentSequenceSegment = generatedTrajectorySequence.get(i);
            Pose2d currentSequenceSegmentStartPose = currentSequenceSegment.getStartPose();
            Pose2d currentSequenceSegmentEndPose = currentSequenceSegment.getEndPose();
            String currentSequenceSegmentReport = MessageFormat.format("Segment {0}: {1} | {2}", i, currentSequenceSegmentStartPose, currentSequenceSegmentEndPose);
        }
    }
}

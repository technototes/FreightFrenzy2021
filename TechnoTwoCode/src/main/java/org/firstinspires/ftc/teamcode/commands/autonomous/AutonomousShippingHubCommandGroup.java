package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutonomousShippingHubCommandGroup extends SequentialCommandGroup {
    public AutonomousShippingHubCommandGroup(DrivebaseSubsystem drive, BucketSubsystem bucket) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.START_TO_SHIPPING_HUB), // Different duck const
                new AutonomousBucketDumpCommand(bucket).withTimeout(3), // Bucket command
                new TrajectorySequenceCommand(drive, AutonomousConstants.SHIPPING_HUB_TO_DEPOT), // Differe park command
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutoRedShippingHubCommandGroup extends SequentialCommandGroup {
    public AutoRedShippingHubCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket) {
        super(new DumpCarryCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_START_TO_SHIPPING_HUB), // Different duck const
                new AutonomousBucketDumpCommand(bucket).withTimeout(3), // Bucket command
                new WaitCommand(1),
                new DumpCollectCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_SHIPPING_HUB_TO_DEPOT), // Differe park command
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}

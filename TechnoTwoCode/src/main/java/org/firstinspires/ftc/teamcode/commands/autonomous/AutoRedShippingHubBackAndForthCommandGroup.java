package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedShippingHubBackAndForthCommandGroup extends SequentialCommandGroup {
    public AutoRedShippingHubBackAndForthCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(new DumpCarryCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_START_TO_SHIPPING_HUB), // Different duck const
                new AutonomousBucketDumpCommand(bucket).withTimeout(3), // Bucket command
                new WaitCommand(1),
                new DumpCollectCommand(bucket),
                new AutonomousLoopCommandGroup(drive,bucket,intake),
                new AutonomousLoopCommandGroup(drive,bucket,intake),
                new AutonomousLoopCommandGroup(drive,bucket,intake),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_SHIPPING_HUB_TO_DEPOT),
                // new // Different park command TODO: /\/\/\ CHECK IF THIS IS GOOD RYAN TIO
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}
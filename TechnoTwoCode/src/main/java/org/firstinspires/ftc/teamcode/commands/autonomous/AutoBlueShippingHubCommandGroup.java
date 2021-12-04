package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutoBlueShippingHubCommandGroup extends SequentialCommandGroup {
    public AutoBlueShippingHubCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket) {
        super(new DumpCarryCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3), // Different duck constant
                new AutonomousBucketDumpCommand(bucket).withTimeout(3), // Bucket command
                new WaitCommand(1),
                new DumpCollectCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_ALLIANCE_HUB_LEVEL3_TO_DEPOT), // Differe park command
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}

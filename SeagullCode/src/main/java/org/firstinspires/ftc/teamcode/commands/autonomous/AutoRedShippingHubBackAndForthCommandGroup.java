package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedShippingHubBackAndForthCommandGroup extends SequentialCommandGroup {
    public AutoRedShippingHubBackAndForthCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(new DumpCarryCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3), // Different duck const
                new DumpUnloadTopLevelCommand(bucket).withTimeout(3), // Bucket command
                new WaitCommand(0.5),
                new DumpCollectCommand(bucket),
                new AutonomousLoopCommandGroup(drive,bucket,intake),
                new AutonomousLoopCommandGroup(drive,bucket,intake),
                new AutonomousLoopCommandGroup(drive,bucket,intake),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT),
                // new // Different park command TODO: /\/\/\ CHECK IF THIS IS GOOD RYAN TIO
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}
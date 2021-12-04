package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedShippingHubCommandGroup extends SequentialCommandGroup {
    public AutoRedShippingHubCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(
            new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_START_TO_ALLIANCE_HUB),
            new AutonomousBucketDumpCommand(bucket).withTimeout(1.75), // Bucket dump command
            new WaitCommand(0.1),
            new DumpCollectCommand(bucket),
            new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT),
            new IntakeInCommand(intake), // Intake command
            new WaitCommand(0.1), //Intake command
            new IntakeStopCommand(intake), //Intake command
            new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_TO_ALLIANCE_HUB_LEVEL3),
            new AutonomousBucketDumpCommand(bucket).withTimeout(1.75), // Bucket dump command
            new WaitCommand(0.1), // Bucket dump command
            new DumpCollectCommand(bucket), // Bucket dump command
            new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT), //probably run out of time at here
            new IntakeInCommand(intake), // Intake command
            new WaitCommand(0.1), //Intake command
            new IntakeStopCommand(intake), //Intake command
            new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_TO_ALLIANCE_HUB_LEVEL3),
            new AutonomousBucketDumpCommand(bucket).withTimeout(1.75), // Bucket dump command
            new WaitCommand(0.1), // Bucket dump command
            new DumpCollectCommand(bucket), // Bucket dump command
            new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT),
            CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}
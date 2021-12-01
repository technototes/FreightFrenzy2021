package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand2;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutonomousLoopCommandGroup extends SequentialCommandGroup {
    public AutonomousLoopCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake){
        super(new DumpCollectCommand2(bucket),
            new AutonomousBucketDumpCommand(bucket).withTimeout(3), // Bucket command
                new WaitCommand(1),
                new DumpCollectCommand2(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_SHIPPING_HUB_TO_DEPOT),
                new IntakeInCommand(intake),
                new WaitCommand(1),
                new IntakeStopCommand(intake),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_TO_SHIPPING_HUB));
    }
}

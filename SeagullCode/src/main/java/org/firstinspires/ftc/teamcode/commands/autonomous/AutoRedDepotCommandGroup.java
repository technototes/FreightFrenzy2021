package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedDepotCommandGroup extends SequentialCommandGroup {
    public AutoRedDepotCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(
                new UnloadTopRedDepotCommandGroup(drive, bucket, intake),
                new RedDepotRemainderCommandGroup(drive, bucket, intake),

                CommandScheduler.getInstance()::terminateOpMode // ending the entire opmode
        );
    }
}
package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoBlueDepotCommandGroup extends SequentialCommandGroup {
    public AutoBlueDepotCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake, VisionSubsystem vision) {
        super(
                new UnloadTopBlueDepotCommandGroup(drive, bucket, intake), // Run the first half of the auto, at the top level
                new BlueDepotRemainderCommandGroup(drive, bucket, intake),

                CommandScheduler.getInstance()::terminateOpMode // ending the entire opmode
        );
    }
}

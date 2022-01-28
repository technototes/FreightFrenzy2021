package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoRedDepotVizCommandGroup extends SequentialCommandGroup {
    public AutoRedDepotVizCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake, VisionSubsystem vision) {
        super(
                new HeightSelectRedDepotCommand(drive, bucket, intake, vision),
                new RedDepotRemainderCommandGroup(drive, bucket, intake),

                CommandScheduler.getInstance()::terminateOpMode // ending the entire opmode
        );
    }

}

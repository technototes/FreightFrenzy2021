package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoRedDuckVizCommandGroup extends SequentialCommandGroup {
    public AutoRedDuckVizCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake, VisionSubsystem vision) {
        super(new HeightSelectRedDuckCommand(drive, bucket, intake, vision),
                  new RedDuckRemainderCommandGroup(drive, bucket, intake, vision),
                  CommandScheduler.getInstance()::terminateOpMode);
    }

}

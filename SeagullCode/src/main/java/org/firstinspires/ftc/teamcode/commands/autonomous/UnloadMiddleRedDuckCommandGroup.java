package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class UnloadMiddleRedDuckCommandGroup extends SequentialCommandGroup {
    public UnloadMiddleRedDuckCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL2), // Different duck constant
                new DumpUnloadMiddleLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(1),
                new DumpCollectCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_ALLIANCE_HUB_LEVEL2_TO_CAROUSEL) // Different park command
        );
    }
}

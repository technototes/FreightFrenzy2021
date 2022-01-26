package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class UnloadTopRedDuckCommandGroup extends SequentialCommandGroup {
    public UnloadTopRedDuckCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL3), // Different duck constant
                new DumpUnloadTopLevelCommand(bucket),
                new WaitCommand(0.7),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL) // Different park command
        );
    }
}

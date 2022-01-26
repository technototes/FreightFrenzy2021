package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class UnloadTopBlueDuckCommandGroup extends SequentialCommandGroup {
    public UnloadTopBlueDuckCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_3), // Different duck constant
                new DumpUnloadTopLevelCommand(bucket),
                new WaitCommand(0.7),
                new DumpCollectCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL) // Different park command
        );
    }
}

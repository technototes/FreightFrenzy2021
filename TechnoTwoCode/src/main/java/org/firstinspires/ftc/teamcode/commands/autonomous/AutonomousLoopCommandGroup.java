package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutonomousLoopCommandGroup extends SequentialCommandGroup {
    public AutonomousLoopCommandGroup(DrivebaseSubsystem drivebaseSystem, DumpSubsystem bucketSystem){
//        new TrajectorySequenceCommand(); //from shelf to depot
        new DepotLoadingBlockCommand();
//        new TrajectorySequenceCommand(); //from depot to shelf
        new ShelfUnloadingLevel3Command();
    }
}

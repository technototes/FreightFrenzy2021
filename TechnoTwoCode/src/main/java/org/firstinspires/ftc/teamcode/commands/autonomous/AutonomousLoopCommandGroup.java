package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutonomousLoopCommandGroup extends SequentialCommandGroup {
    public AutonomousLoopCommandGroup(DrivebaseSubsystem drivebaseSystem, BucketSubsystem bucketSystem){
//        new TrajectorySequenceCommand(); //from shelf to depot
        new DepotLoadingBlockCommand();
//        new TrajectorySequenceCommand(); //from depot to shelf
        new ShelfUnloadingLevel3Command();
    }
}

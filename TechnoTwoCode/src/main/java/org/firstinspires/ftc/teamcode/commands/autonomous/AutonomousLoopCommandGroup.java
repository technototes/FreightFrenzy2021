package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutonomousLoopCommandGroup extends SequentialCommandGroup {
    public AutonomousLoopCommandGroup(DrivebaseSubsystem drivebaseSystem, DumpSubsystem bucketSystem){
 //    new TrajectorySequenceCommand(drivebaseSystem, AutonomousConstants.SHIPPING_HUB_TO_DEPOT); //from shelf to depot
        new DepotLoadingBlockCommand();
//      new TrajectorySequenceCommand(drivebaseSystem, AutonomousConstants.); //from depot to shelf
        new ShelfUnloadingLevel3Command();
    }
}

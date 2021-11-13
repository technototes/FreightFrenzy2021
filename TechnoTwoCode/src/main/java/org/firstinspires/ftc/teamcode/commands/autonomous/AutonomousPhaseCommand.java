/**
 * equivalent to AutonomousCycleCommand.java in TechnoOne
 */
package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutonomousPhaseCommand extends SequentialCommandGroup {
    public AutonomousPhaseCommand(DrivebaseSubsystem drivebaseSystem, CarouselSubsystem carouselSystem, DumpSubsystem bucketSystem){
        super(new AutonomousDuckCommandGroup(drivebaseSystem, carouselSystem), //goto carousel and spin it
//                new TrajectorySequenceCommand(), //duck goto shipping hub
                new ShelfUnloadingLevel3Command(), //unload the preloaded cube
                new AutonomousLoopCommandGroup(drivebaseSystem, bucketSystem), //assuming running for three loops
                new AutonomousLoopCommandGroup(drivebaseSystem, bucketSystem),
                new AutonomousLoopCommandGroup(drivebaseSystem, bucketSystem),
//                new TrajectorySequenceCommand(), //goto parking
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutoBlueDuckCollectCommand extends SequentialCommandGroup {
    public AutoBlueDuckCollectCommand(DrivebaseSubsystem drive){
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_COLLECT1_TO_DUCK_COLLECT2),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_COLLECT2_TO_DUCK_COLLECT1),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_COLLECT1_TO_DUCK_COLLECT2),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_COLLECT2_TO_DUCK_COLLECT1),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_COLLECT1_TO_DUCK_COLLECT2),
                CommandScheduler.getInstance()::terminateOpMode);
    }

}

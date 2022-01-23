package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutoRedDuckCollectCommand extends SequentialCommandGroup {
    public AutoRedDuckCollectCommand(DrivebaseSubsystem drive){
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT1_TO_COLLECT2),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT2_TO_COLLECT1),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT1_TO_COLLECT2));
    }

}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class AutoRedDuckCommandGroup extends SequentialCommandGroup {
    public AutoRedDuckCommandGroup(DrivebaseSubsystem drive, CarouselSubsystem carousel) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_START_TO_CAROUSEL),
                  new CarouselSpinCommand(carousel).withTimeout(3),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_CAROUSEL_TO_PARK),
                  CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}


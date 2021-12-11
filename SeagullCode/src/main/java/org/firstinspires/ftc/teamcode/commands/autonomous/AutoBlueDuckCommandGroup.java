package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class AutoBlueDuckCommandGroup extends SequentialCommandGroup {
    public AutoBlueDuckCommandGroup(DrivebaseSubsystem drive, CarouselSubsystem carousel, DumpSubsystem dump) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3),
                  new AutonomousBucketDumpCommand(dump),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL),
                  new CarouselSpinCommand(carousel).withTimeout(3),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_CAROUSEL_TO_PARK),
                  CommandScheduler.getInstance()::terminateOpMode);
    }
}


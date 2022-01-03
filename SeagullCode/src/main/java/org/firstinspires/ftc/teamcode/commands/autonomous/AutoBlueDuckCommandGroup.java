package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.AutoCarouselSpinCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class AutoBlueDuckCommandGroup extends SequentialCommandGroup {
    public AutoBlueDuckCommandGroup(DrivebaseSubsystem drive, CarouselSubsystem carousel, DumpSubsystem bucket) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_3),
                new DumpUnloadTopLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(0.3),
                new DumpCollectCommand(bucket),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL),
                  new AutoCarouselSpinCommand(carousel).withTimeout(3),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_CAROUSEL_TO_PARK),
                  CommandScheduler.getInstance()::terminateOpMode);
    }
}


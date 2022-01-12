package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.AutoCarouselSpinCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedDuckCommandGroup extends SequentialCommandGroup {
    public AutoRedDuckCommandGroup(DrivebaseSubsystem drive, CarouselSubsystem carousel, DumpSubsystem dump, IntakeSubsystem intake) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL3),
                  new AutonomousBucketDumpCommand(dump),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_ALLIANCE_HUB_TO_CAROUSEL),
                  new AutoCarouselSpinCommand(carousel).withTimeout(4),
                  new DumpCarryCommand(dump),
                  new DumpCollectCommand(dump),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_CAROUSEL_TO_COLLECT1),
                  new IntakeInCommand(intake),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT1_TO_COLLECT2),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT2_TO_COLLECT1),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT1_TO_COLLECT2),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT2_TO_COLLECT1),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT1_TO_COLLECT2),
                  new IntakeStopCommand(intake),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT2_TO_ALLIANCE_HUB_LEVEL3),
                  new AutonomousBucketDumpCommand(dump),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_ALLIANCE_HUB_LEVEL3_TO_PARK),
                  new DumpCarryCommand(dump),
                  new DumpCollectCommand(dump),
                  CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}


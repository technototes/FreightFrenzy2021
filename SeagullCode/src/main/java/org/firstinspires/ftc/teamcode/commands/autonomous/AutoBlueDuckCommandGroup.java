package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoBlueDuckCommandGroup extends SequentialCommandGroup {
    public AutoBlueDuckCommandGroup(DrivebaseSubsystem drive, CarouselSubsystem carousel, DumpSubsystem dump, IntakeSubsystem intake) {
        super(
                new UnloadTopBlueDuckCommandGroup(drive, dump, intake),
                new BlueDuckRemainderCommandGroup(drive, dump, intake, carousel),

                CommandScheduler.getInstance()::terminateOpMode // ending the entire opmode
        );
    }
}


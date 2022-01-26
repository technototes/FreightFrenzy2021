package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedDuckCommandGroup extends SequentialCommandGroup {
    public AutoRedDuckCommandGroup(DrivebaseSubsystem drive, CarouselSubsystem carousel, DumpSubsystem dump, IntakeSubsystem intake) {
        super(
                new UnloadTopRedDuckCommandGroup(drive, dump),
                new RedDuckRemainderCommandGroup(drive, dump, intake, carousel),

                CommandScheduler.getInstance()::terminateOpMode // ending the entire opmode
        );
    }
}


package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoBlueDuckVizCommandGroup extends SequentialCommandGroup {
    public AutoBlueDuckVizCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake, VisionSubsystem vision, CarouselSubsystem carousel) {
        super(
                new HeightSelectBlueDuckCommand(drive, bucket, vision),
                new BlueDuckRemainderCommandGroup(drive, bucket, intake, carousel),

                CommandScheduler.getInstance()::terminateOpMode // ending the entire opmode
        );
    }

}

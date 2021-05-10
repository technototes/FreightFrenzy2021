package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.ParallelRaceGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public class BouncebackCommand extends SequentialCommandGroup {
    public BouncebackCommand(DrivebaseSubsystem d, ShooterSubsystem s, IntakeSubsystem i, IndexSubsystem ix, TurretSubsystem t, AutoState st) {
        super(
                //bounceback time
                new ParallelCommandGroup(
                        new StrafeCommand(d, st.correctedPos(110, 50, -90)),
                        new ShooterSetSpeed2Command(s, () -> 10),
                        new IntakeInCommand(i)
                ),
                new StrafeCommand(d, st.correctedPos(110, 0, -90)),
                new IntakeStopCommand(i)
        );
    }
}

package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.bot2.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.TurretSubsystem;

public class BouncebackCommand extends SequentialCommandGroup {
    public BouncebackCommand(DrivebaseSubsystem d, ShooterSubsystem s, IntakeSubsystem i, IndexSubsystem ix, TurretSubsystem t, AutoState st) {
        super(
                //bounceback time
                new ParallelCommandGroup(
                        new StrafeCommand(d, st.correctedPos(115, 50, -45)),
                        new ShooterSetSpeed2Command(s, () -> 10),
                        new IntakeInCommand(i)
                ),
                new ParallelCommandGroup(
                        new StrafeCommand(d, st.correctedPos(115, 0, -45)),
                        new SequentialCommandGroup(
                                new WaitCommand(3),
                                new IntakeStopCommand(i)
                        )
                )
        );
    }
}

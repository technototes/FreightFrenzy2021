package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public class PowershotCommand extends SequentialCommandGroup {
    public PowershotCommand(DrivebaseSubsystem d, ShooterSubsystem s, IntakeSubsystem i, IndexSubsystem ix, TurretSubsystem t, AutoState st) {
        super(
                new ParallelCommandGroup(
                        new StrafeCommand(d, st.correctedPos(58, 35, 0)),
                        new IntakeStopCommand(i),
                        new ShooterSetSpeed2Command(s, () -> 1000).with(new ShooterSetFlapCommand(s, () -> 0.74))
                ),
                //it doesnt actually shoot this one
                new InstantCommand(() -> t.setTurretPosition(0.1)),
                new ShooterSetSpeed2Command(s, () -> 1000),
                new SendOneRingToShooterCommand(ix),
                //it shoots this one
                new InstantCommand(() -> t.setTurretPosition(0.14)),
                new ShooterSetSpeed2Command(s, () -> 1130),
                new SendOneRingToShooterCommand(ix),
                new InstantCommand(() -> t.setTurretPosition(0.28)),
                new ShooterSetSpeed2Command(s, () -> 1130),
                new SendOneRingToShooterCommand(ix)

        );

    }
}

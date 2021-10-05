package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.bot2.commands.turret.TurretRotateCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.TurretSubsystem;

public class PowershotCommand extends SequentialCommandGroup {
    public PowershotCommand(DrivebaseSubsystem d, ShooterSubsystem s, IntakeSubsystem i, IndexSubsystem ix, TurretSubsystem t, AutoState st) {
        super(
                new ParallelCommandGroup(
                        new StrafeCommand(d, st.correctedPos(58, 35, 0)),
                        new IntakeStopCommand(i),
                        new ShooterSetSpeed2Command(s, () -> 1000),
                        new ShooterSetFlapCommand(s, () -> 0.79)
                ),
                //Just checking
                //new StrafeCommand(d, st.correctedPos(58, 35, -1)),
                //it doesnt actually shoot this one
                new TurretRotateCommand(t, 0.05),
                new ShooterSetSpeed2Command(s, () -> 1000),
                new SendOneRingToShooterCommand(ix),
                //it shoots this one
                new TurretRotateCommand(t, 0.13),
                new ShooterSetSpeed2Command(s, () -> 1160),
                new SendOneRingToShooterCommand(ix),
                //it shoots this one
                new TurretRotateCommand(t, 0.295),
                new ShooterSetSpeed2Command(s, () -> 1150),
                new SendOneRingToShooterCommand(ix),
                //it shoots this one
                new TurretRotateCommand(t, 0.43),
                new ShooterSetSpeed2Command(s, () -> 1150),
                new SendOneRingToShooterCommand(ix)

        );

    }
}

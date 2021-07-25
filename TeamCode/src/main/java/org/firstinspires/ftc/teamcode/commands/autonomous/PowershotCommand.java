package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.commands.turret.TurretRotateCommand;
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

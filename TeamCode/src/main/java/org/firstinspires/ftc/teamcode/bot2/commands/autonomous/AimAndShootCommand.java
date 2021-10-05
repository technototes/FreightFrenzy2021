package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.VisionAimSubsystem;

public class AimAndShootCommand extends SequentialCommandGroup {
    public AimAndShootCommand(IntakeSubsystem i, IndexSubsystem ix, TurretSubsystem t, VisionAimSubsystem v, ShooterSubsystem s){
        super(
                new IntakeStopCommand(i),
//                new ArmRetractCommand(ix),

                // Must be done by previous command, to save time
                //new ShooterSetSpeed2Command(s, () -> 1310).with(new ShooterSetFlapCommand(s, () -> 0.85)),

                new SendOneRingToShooterCommand(ix),
                new ShooterSetSpeed2Command(s, ()->1310),
                new SendOneRingToShooterCommand(ix),
                new ShooterSetSpeed2Command(s, ()->1310),
                new SendOneRingToShooterCommand(ix),
                new ShooterSetSpeed2Command(s, ()->1310),
                new SendOneRingToShooterCommand(ix)
                );
    }
}

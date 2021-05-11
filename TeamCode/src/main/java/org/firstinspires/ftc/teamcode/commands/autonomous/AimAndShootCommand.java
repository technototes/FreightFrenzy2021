package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;

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

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;

public class AimAndShootCommand extends SequentialCommandGroup {
    public AimAndShootCommand(IndexSubsystem i, TurretSubsystem t, VisionAimSubsystem v, ShooterSubsystem s){
        super(
                new ArmRetractCommand(i).with(new InstantCommand(()->t.setTurretPosition(0.25))).sleep(0.5),

                new SendOneRingToShooterCommand(i).then(new ShooterSetSpeed2Command(s, ()->1310)),
                new SendOneRingToShooterCommand(i).then(new ShooterSetSpeed2Command(s, ()->1310)),
                new SendOneRingToShooterCommand(i).then(new ShooterSetSpeed2Command(s, ()->1310)),
                new SendOneRingToShooterCommand(i)

                );
    }
}

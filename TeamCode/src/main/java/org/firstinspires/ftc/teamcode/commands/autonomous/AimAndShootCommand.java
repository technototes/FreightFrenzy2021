package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;

public class AimAndShootCommand extends SequentialCommandGroup {
    public AimAndShootCommand(IndexSubsystem i, TurretSubsystem t, VisionAimSubsystem v, ShooterSubsystem s){
        super(
                new ArmRetractCommand(i).with(new InstantCommand(()->t.setTurretPosition(0.3))),

                new SendOneRingToShooterCommand(i).until(()->s.getVelocity()>=1300),
                new SendOneRingToShooterCommand(i).until(()->s.getVelocity()>=1300),
                new SendOneRingToShooterCommand(i).until(()->s.getVelocity()>=1300)

                );
    }
}

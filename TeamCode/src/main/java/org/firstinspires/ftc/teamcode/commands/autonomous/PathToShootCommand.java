package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.commands.turret.TurretRotateCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public class PathToShootCommand extends SequentialCommandGroup {
    public PathToShootCommand(DrivebaseSubsystem d, ShooterSubsystem s, IntakeSubsystem i, TurretSubsystem t, AutoState st) {
        super(new ParallelCommandGroup(
                        //new IntakeInCommand(i),
                        new StrafeCommand(d, st.correctedPos(55, 14, 0)),
                        new TurretRotateCommand(t, 0.27),
                        new ShooterSetSpeed2Command(s, () -> 1310),
                        new ShooterSetFlapCommand(s, () -> 0.85)
                )//,
                //new IntakeStopCommand(i)
        );

    }
}

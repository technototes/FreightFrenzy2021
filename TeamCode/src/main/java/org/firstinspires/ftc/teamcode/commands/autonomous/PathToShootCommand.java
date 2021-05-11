package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeed2Command;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeedCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;

public class PathToShootCommand extends SequentialCommandGroup {
    public PathToShootCommand(DrivebaseSubsystem d, ShooterSubsystem s, IntakeSubsystem i, TurretSubsystem t, AutoState st) {
        super(new ParallelCommandGroup(
                        //new IntakeInCommand(i),
                        new StrafeCommand(d, st.correctedPos(55, 14, 0)),
                        new InstantCommand(()->t.setTurretPosition(0.27)),
                        new ShooterSetSpeed2Command(s, () -> 1310),
                        new ShooterSetFlapCommand(s, () -> 0.85)
                )//,
                //new IntakeStopCommand(i)
        );

    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeStackCommand extends SequentialCommandGroup {
    public IntakeStackCommand(DrivebaseSubsystem d, IntakeSubsystem i, AutoState s) {
        super(
                new StrafeCommand(d, s.correctedPos(50, 12, 0)),
                new StrafeCommand(d, s.correctedPos(45, 16, 20)).with(new IntakeInCommand(i)),
                new WaitCommand(0.5),
                new IntakeStopCommand(i),
                new IntakeInCommand(i),
                new WaitCommand(1.5),
                new IntakeStopCommand(i)
        );
    }
}

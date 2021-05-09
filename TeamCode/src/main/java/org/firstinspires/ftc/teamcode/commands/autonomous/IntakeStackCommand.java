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
                new IntakeInCommand(i).with(new StrafeCommand(d, s.correctedPos(30, 0, 0))),
                new WaitCommand(1),
                new IntakeStopCommand(i)
        );
    }
}

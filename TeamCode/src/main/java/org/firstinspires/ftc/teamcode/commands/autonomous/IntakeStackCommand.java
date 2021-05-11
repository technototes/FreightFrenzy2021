package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.Command;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeStackCommand extends SequentialCommandGroup {
    public IntakeStackCommand(DrivebaseSubsystem d, IntakeSubsystem i, AutoState s) {
        super(
                //new InstantCommand(()->d.setWeightedDrivePower(new Pose2d(-0.3, 0, 0))),
                new IntakeInCommand(i),
                new StrafeCommand(d, s.correctedPos(51, 13, 0)),
                new WaitCommand(0.3),
                //new IntakeInCommand(i),
                new StrafeCommand(d, s.correctedPos(47, 12, 0)),
                new WaitCommand(0.3),
                new StrafeCommand(d, s.correctedPos(44, 11, 0)),
                new WaitCommand(0.3),
//                new StrafeCommand(d, s.correctedPos(41, 11, 0))
                new StrafeCommand(d, s.correctedPos(44, 25, 0)),
//                new WaitCommand(2)
//                new IntakeStopCommand(i)
//                new IntakeInCommand(i),
//                new WaitCommand(1.5),
                new IntakeStopCommand(i)
        );
    }
}

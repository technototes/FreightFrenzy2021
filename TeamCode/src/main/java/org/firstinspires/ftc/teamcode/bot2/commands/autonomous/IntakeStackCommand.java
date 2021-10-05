package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.bot2.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.IntakeSubsystem;

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

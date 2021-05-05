package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class ParkCommand extends SequentialCommandGroup {
    public ParkCommand(DrivebaseSubsystem s, WobbleSubsystem w, AutoState a) {
        super(
                new StrafeCommand(s, a.correctedEndPos()).with( //80 10 82
                        new WobbleRaiseCommand(w).then(new WobbleCloseCommand(w))),
                new InstantCommand(() -> s.setPoseEstimate(new Pose2d())));
    }
}

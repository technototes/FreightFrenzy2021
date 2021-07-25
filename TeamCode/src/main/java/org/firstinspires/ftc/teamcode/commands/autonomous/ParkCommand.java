package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class ParkCommand extends SequentialCommandGroup {
    public ParkCommand(DrivebaseSubsystem s, WobbleSubsystem w, AutoState a) {
        super(
                new StrafeCommand(s, a.correctedEndPos()).alongWith( //80 10 82
                        new WobbleRaiseCommand(w).andThen(new WobbleCloseCommand(w))),
                new OldInstantCommand(() -> s.setPoseEstimate(new Pose2d())));
    }
}

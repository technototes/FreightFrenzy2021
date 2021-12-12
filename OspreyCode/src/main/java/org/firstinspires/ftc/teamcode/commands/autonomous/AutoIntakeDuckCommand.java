package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoIntakeDuckCommand extends SequentialCommandGroup {
    public AutoIntakeDuckCommand(DrivebaseSubsystem drive, IntakeSubsystem intake) {
        super(new TrajectorySequenceCommand(drive, RobotConstants.CAROUSEL_TO_DUCK_INTAKE)
                        .alongWith(new IntakeInCommand(intake)),
                new WaitCommand(1));
    }
}

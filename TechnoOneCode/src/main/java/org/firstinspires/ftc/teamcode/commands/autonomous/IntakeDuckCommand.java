package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.RelocalizeDuckCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class IntakeDuckCommand extends SequentialCommandGroup {
    public IntakeDuckCommand(DrivebaseSubsystem drive, IntakeSubsystem intake) {
        super(new RelocalizeDuckCommand(drive),
                new TrajectorySequenceCommand(drive, AutonomousConstants.DUCK_CAROUSEL_TO_INTAKE)
        .alongWith(new IntakeInCommand(intake)));
    }
}

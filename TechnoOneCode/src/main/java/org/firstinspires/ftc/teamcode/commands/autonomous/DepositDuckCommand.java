package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class DepositDuckCommand extends SequentialCommandGroup {
    public DepositDuckCommand(DrivebaseSubsystem drive, DepositSubsystem depot, LiftSubsystem lift, IntakeSubsystem intake) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.DUCK_INTAKE_TO_DEPOSIT)
                .alongWith(new LiftLevel3Command(lift).withTimeout(1.5), new ArmExtendCommand(depot), new IntakeOutCommand(intake).withTimeout(0.5)),
                new DumpCommand(depot));
    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.RegenerativeTrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.CarryCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.RelocalizeCycleCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositFreightCommand extends SequentialCommandGroup {
    public DepositFreightCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit){
        super(new RelocalizeCycleCommand(drive),
                new RegenerativeTrajectorySequenceCommand(drive, AutonomousConstants.CYCLE_COLLECT_TO_DEPOSIT, drive)
                        .alongWith(new IntakeOutCommand(intake).withTimeout(0.5),
                        new CarryCommand(deposit),
                        new WaitCommand(0.2).andThen(new RelocalizeCycleCommand(drive)),
                        new WaitCommand(0.5).andThen(
                                new LiftLevel3Command(lift).withTimeout(1)),
                                new ArmExtendCommand(deposit)),
                new DumpCommand(deposit));
    }
}

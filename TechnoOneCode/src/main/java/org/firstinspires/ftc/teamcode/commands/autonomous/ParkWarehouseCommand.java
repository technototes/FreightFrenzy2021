package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class ParkWarehouseCommand extends SequentialCommandGroup {
    public ParkWarehouseCommand(DrivebaseSubsystem drive, LiftSubsystem lift, DepositSubsystem deposit){
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.CYCLE_DEPOSIT_TO_COLLECT)
                .alongWith(new WaitCommand(0.5).andThen(new LiftCollectCommand(lift)), new ArmRetractCommand(deposit)));
    }
}

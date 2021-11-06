package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class ParkCommand extends SequentialCommandGroup {
    public ParkCommand(DrivebaseSubsystem drive, LiftSubsystem lift, DepositSubsystem deposit){
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.DEPOSIT_TO_COLLECT)
                .alongWith(new LiftCollectCommand(lift), new ArmRetractCommand(deposit)));
    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class ParkSquareCommand extends SequentialCommandGroup {
    public ParkSquareCommand(DrivebaseSubsystem drive, LiftSubsystem lift, DepositSubsystem deposit){
        super(new WaitCommand(0.2).andThen(new TrajectorySequenceCommand(drive, AutonomousConstants.DUCK_DEPOSIT_TO_PARK).alongWith(
                new WaitCommand(0.5).andThen(new LiftCollectCommand(lift)), new ArmRetractCommand(deposit))
        ));
    }
}

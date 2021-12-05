package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoParkSquareCommand extends SequentialCommandGroup {
    public AutoParkSquareCommand(DrivebaseSubsystem drive, LiftSubsystem lift, DepositSubsystem deposit, ExtensionSubsystem extension){
        super(new WaitCommand(0.2).andThen(new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_SQUARE).alongWith(
                new WaitCommand(0.5).andThen(new LiftCollectCommand(lift))),
                new ArmInCommand(deposit)
        ));
    }
}

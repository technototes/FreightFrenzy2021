package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoParkWarehouseCommand extends SequentialCommandGroup {
    public AutoParkWarehouseCommand(DrivebaseSubsystem drive, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension){
        super(new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_PARK)
                .alongWith(new DepositCollectCommand(deposit, extension, lift)));
    }
}

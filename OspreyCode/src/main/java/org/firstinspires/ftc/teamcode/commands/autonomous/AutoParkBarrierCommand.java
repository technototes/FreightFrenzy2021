package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoParkBarrierCommand extends SequentialCommandGroup {
    public AutoParkBarrierCommand(DrivebaseSubsystem drive, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension){
        super(new DepositCollectCommand(deposit, extension, lift),
                new ConditionalCommand(()-> CommandScheduler.getInstance().getOpModeRuntime()>25),
                new TrajectorySequenceCommand(drive, RobotConstants.HUB_BARRIER_PARK));
    }
}

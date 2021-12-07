package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositPreloadCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoCyclePreloadCommand extends SequentialCommandGroup {
    public AutoCyclePreloadCommand(DrivebaseSubsystem drive, ArmSubsystem depot, ExtensionSubsystem extension, LiftSubsystem lift, VisionSubsystem vision) {
        super(new TrajectorySequenceCommand(drive, RobotConstants.CYCLE_DEPOSIT_PRELOAD)
                //.alongWith(new LiftBarcodeSelectCommand(lift, vision)
                .alongWith(new DepositPreloadCommand(depot, extension, lift, vision)),
                new BucketDumpCommand(depot).sleep(0.3));
    }
}

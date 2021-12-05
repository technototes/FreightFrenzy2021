package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.BucketDumpCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoDuckPreloadCommand extends SequentialCommandGroup {
    public AutoDuckPreloadCommand(DrivebaseSubsystem drive, DepositSubsystem depot, ExtensionSubsystem extension, LiftSubsystem lift, VisionSubsystem vision) {
        super(new TrajectorySequenceCommand(drive, RobotConstants.DUCK_DEPOSIT_PRELOAD)
                //.alongWith(new LiftBarcodeSelectCommand(lift, vision)
                .alongWith(new LiftBarcodeSelectCommand(lift, vision).withTimeout(1.5), new ArmOutCommand(depot)),
                new BucketDumpCommand(depot),
                new WaitCommand(0.3));
    }
}

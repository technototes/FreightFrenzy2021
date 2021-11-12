package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class DepositDuckPreloadCommand extends SequentialCommandGroup {
    public DepositDuckPreloadCommand(DrivebaseSubsystem drive, DepositSubsystem depot, LiftSubsystem lift, VisionSubsystem vision) {
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.DUCK_START_TO_DEPOSIT)
                //.alongWith(new LiftBarcodeSelectCommand(lift, vision)
                .alongWith(new LiftBarcodeSelectCommand(lift, vision).withTimeout(1.5), new ArmExtendCommand(depot)),
                new DumpCommand(depot));
    }
}

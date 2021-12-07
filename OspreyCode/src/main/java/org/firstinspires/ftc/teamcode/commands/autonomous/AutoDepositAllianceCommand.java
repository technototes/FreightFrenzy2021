package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.RegenerativeTrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.BucketCarryCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.BucketDumpCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveRelocalizeCycleCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoDepositAllianceCommand extends SequentialCommandGroup {
    public AutoDepositAllianceCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, ExtensionSubsystem extension){
        super(new DriveRelocalizeCycleCommand(drive),
                new RegenerativeTrajectorySequenceCommand(drive, RobotConstants.WAREHOUSE_TO_HUB, drive)
                        .alongWith(new IntakeOutCommand(intake).withTimeout(0.5),
                        new BucketCarryCommand(deposit),
                        new WaitCommand(0.2).andThen(new DriveRelocalizeCycleCommand(drive)),
                        new WaitCommand(0.5).andThen(
                                new LiftLevel3Command(lift).withTimeout(1)),
                                new ArmOutCommand(deposit)),
                new BucketDumpCommand(deposit));
    }
}

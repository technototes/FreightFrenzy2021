package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.RegenerativeTrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveRelocalizeCycleCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoDepositAllianceCommand extends SequentialCommandGroup {
    public AutoDepositAllianceCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension) {
        super(new DriveRelocalizeCycleCommand(drive),
                new RegenerativeTrajectorySequenceCommand(drive, RobotConstants.WAREHOUSE_TO_HUB, drive)
                        .alongWith(new IntakeOutCommand(intake).withTimeout(0.5),
                                new WaitCommand(0.3).andThen(new DriveRelocalizeCycleCommand(drive)),
                                new WaitCommand(0.8).andThen(new DepositAllianceCommand(deposit, extension, lift))),
                new BucketDumpCommand(deposit));
    }
}

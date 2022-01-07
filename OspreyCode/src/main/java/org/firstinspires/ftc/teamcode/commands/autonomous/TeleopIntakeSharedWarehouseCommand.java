package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.RobotState;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class TeleopIntakeSharedWarehouseCommand extends SequentialCommandGroup {
    public DrivebaseSubsystem drivebaseSubsystem;
    public TeleopIntakeSharedWarehouseCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension) {
        super(new TrajectorySequenceCommand(drive, RobotConstants.SHARED_HUB_TO_WAREHOUSE)
                .alongWith(new DepositCollectCommand(deposit, extension, lift).sleep(0.3).andThen(new IntakeInCommand(intake))),
                new WaitCommand(0.3));
        drivebaseSubsystem = drive;
    }
    @Override
    public void initialize() {
        super.initialize();
        RobotState.stopDeposit();
    }

}
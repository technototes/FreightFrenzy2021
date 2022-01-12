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

public class TeleopIntakeAllianceWarehouseCommand extends SequentialCommandGroup {
    public DrivebaseSubsystem drivebaseSubsystem;
    public TeleopIntakeAllianceWarehouseCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension) {
        super(new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_WAREHOUSE, 4)
                .alongWith(new DepositCollectCommand(deposit, extension, lift).andThen(new IntakeInCommand(intake))),
                new WaitCommand(0.3));
        drivebaseSubsystem = drive;
    }

}
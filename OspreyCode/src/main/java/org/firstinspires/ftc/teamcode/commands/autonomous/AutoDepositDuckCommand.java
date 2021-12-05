package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.BucketDumpCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoDepositDuckCommand extends SequentialCommandGroup {
    public AutoDepositDuckCommand(DrivebaseSubsystem drive, DepositSubsystem depot, ExtensionSubsystem extension, LiftSubsystem lift, IntakeSubsystem intake) {
        super(new TrajectorySequenceCommand(drive, RobotConstants.DUCK_INTAKE_TO_HUB)
                .alongWith(new LiftLevel3Command(lift).withTimeout(1.5), new ArmOutCommand(depot), new IntakeOutCommand(intake).withTimeout(0.5)),
                new BucketDumpCommand(depot));
    }
}

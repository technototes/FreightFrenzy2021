package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.RelocalizeCycleCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositFreightCommand extends SequentialCommandGroup {
    public DepositFreightCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, int cycle){
        super(new RelocalizeCycleCommand(drive),
                new TrajectorySequenceCommand(drive, AutonomousConstants.CYCLE_COLLECT_TO_DEPOSIT, cycle)
                        .alongWith(new IntakeOutCommand(intake).withTimeout(0.5),
                        new WaitCommand(0.2).andThen(
                                new LiftLevel3Command(lift).withTimeout(1)),
                                new ArmExtendCommand(deposit),
                                new RelocalizeCycleCommand(drive)),
                new DumpCommand(deposit));
    }
}

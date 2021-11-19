package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.RelocalizeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositFreightCommand extends SequentialCommandGroup {
    public DepositFreightCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, int cycle){
        super(new RelocalizeCommand(drive),
                new TrajectorySequenceCommand(drive, AutonomousConstants.CYCLE_COLLECT_TO_DEPOSIT/*.apply(e->drive.trajectorySequenceBuilder()*/, cycle)
                .alongWith(new ArmExtendCommand(deposit),
                        new IntakeOutCommand(intake).withTimeout(0.3),
                        new LiftLevel3Command(lift).withTimeout(1)),
                new DumpCommand(deposit));
    }
}

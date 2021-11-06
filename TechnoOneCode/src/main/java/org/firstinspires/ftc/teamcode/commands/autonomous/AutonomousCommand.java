package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ParallelCommandGroup;
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
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutonomousCommand extends SequentialCommandGroup {
    public AutonomousCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, VisionSubsystem vision){
//        super(new DepositPreloadCommand(drive, deposit, lift, vision),
//                new IntakeDepotCommand(drive, intake, lift, deposit),
//                new DepositFreightCommand(drive, intake, lift, deposit),
//                new ParkCommand(drive, lift, deposit));
          super(new TrajectorySequenceCommand(drive, AutonomousConstants.START_TO_DEPOSIT)
                  .alongWith(new LiftLevel3Command(lift), new ArmExtendCommand(deposit)),
                  new DumpCommand(deposit),
                  new ParallelCommandGroup(new TrajectorySequenceCommand(drive, AutonomousConstants.DEPOSIT_TO_COLLECT),
                          new ArmRetractCommand(deposit).andThen(new LiftCollectCommand(lift)))
                          .deadline(new WaitCommand(1).andThen(new IntakeSafeCommand(intake, deposit, false))),
                  new RelocalizeCommand(drive),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.COLLECT_TO_DEPOSIT)
                          .alongWith(new ArmExtendCommand(deposit), new LiftLevel3Command(lift), new IntakeOutCommand(intake).withTimeout(1)),
                  new DumpCommand(deposit),
                  new ParallelCommandGroup(new TrajectorySequenceCommand(drive, AutonomousConstants.DEPOSIT_TO_COLLECT),
                          new ArmRetractCommand(deposit).andThen(new LiftCollectCommand(lift)))
                          .deadline(new WaitCommand(1).andThen(new IntakeSafeCommand(intake, deposit, false))),
                  new RelocalizeCommand(drive),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.COLLECT_TO_DEPOSIT)
                          .alongWith(new ArmExtendCommand(deposit), new LiftLevel3Command(lift), new IntakeOutCommand(intake).withTimeout(1)),
                  new DumpCommand(deposit),
                  new ParallelCommandGroup(new TrajectorySequenceCommand(drive, AutonomousConstants.DEPOSIT_TO_COLLECT),
                          new ArmRetractCommand(deposit).andThen(new LiftCollectCommand(lift)))
                          .deadline(new IntakeSafeCommand(intake, deposit, false)),
                  new RelocalizeCommand(drive),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.COLLECT_TO_DEPOSIT)
                          .alongWith(new ArmExtendCommand(deposit), new LiftLevel3Command(lift), new IntakeOutCommand(intake).withTimeout(1)),
                  new DumpCommand(deposit),
                  new ParallelCommandGroup(new TrajectorySequenceCommand(drive, AutonomousConstants.DEPOSIT_TO_COLLECT),
                          new ArmRetractCommand(deposit).andThen(new LiftCollectCommand(lift)))
                          .deadline(new IntakeSafeCommand(intake, deposit, false)),
                  new RelocalizeCommand(drive),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.COLLECT_TO_DEPOSIT)
                          .alongWith(new ArmExtendCommand(deposit), new LiftLevel3Command(lift), new IntakeOutCommand(intake).withTimeout(1)),
                  new DumpCommand(deposit),
                  new TrajectorySequenceCommand(drive, AutonomousConstants.DEPOSIT_TO_COLLECT)
                          .alongWith(new ArmRetractCommand(deposit).andThen(new LiftCollectCommand(lift))),
                  CommandScheduler.getInstance()::terminateOpMode);
    }
}

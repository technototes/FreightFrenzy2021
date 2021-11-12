package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutonomousCycleCommand extends SequentialCommandGroup {
    public AutonomousCycleCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, VisionSubsystem vision){
        super(new DepositCyclePreloadCommand(drive, deposit, lift, vision),
                new IntakeDepotCommand(drive, intake, lift, deposit, 0),
                new DepositFreightCommand(drive, intake, lift, deposit, 0),
                new IntakeDepotCommand(drive, intake, lift, deposit, 1),
                new DepositFreightCommand(drive, intake, lift, deposit, 1),
                new IntakeDepotCommand(drive, intake, lift, deposit, 2),
                new DepositFreightCommand(drive, intake, lift, deposit, 2),
                new IntakeDepotCommand(drive, intake, lift, deposit, 3),
                new DepositFreightCommand(drive, intake, lift, deposit, 3),
                new ParkWarehouseCommand(drive, lift, deposit),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

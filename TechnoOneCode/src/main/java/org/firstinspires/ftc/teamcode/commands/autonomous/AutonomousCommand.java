package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutonomousCommand extends SequentialCommandGroup {
    public AutonomousCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, VisionSubsystem vision){
        super(new DepositPreloadCommand(drive, deposit, lift, vision),
                new WaitCommand(0.1),
                new ArmRetractCommand(deposit),
                new IntakeDepotCommand(drive, intake, lift, deposit),
                new DepositFreightCommand(drive, intake, lift, deposit),
                new ParkCommand(drive, lift, deposit));
    }
}

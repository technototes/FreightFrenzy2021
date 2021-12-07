package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoCycleCommand extends SequentialCommandGroup {
    public AutoCycleCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, ExtensionSubsystem extension, VisionSubsystem vision){
        super(new AutoCyclePreloadCommand(drive, deposit, extension, lift, vision),
                new AutoIntakeWarehouseCommand(drive, intake, lift, deposit, extension, 0),
                new AutoDepositAllianceCommand(drive, intake, lift, deposit, extension),
                new AutoIntakeWarehouseCommand(drive, intake, lift, deposit, extension, 1),
                new AutoDepositAllianceCommand(drive, intake, lift, deposit, extension),
                new AutoIntakeWarehouseCommand(drive, intake, lift, deposit, extension, 2),
                new AutoDepositAllianceCommand(drive, intake, lift, deposit, extension),
                new AutoIntakeWarehouseCommand(drive, intake, lift, deposit, extension, 3),
                new AutoDepositAllianceCommand(drive, intake, lift, deposit, extension),
                new AutoParkWarehouseCommand(drive, lift, deposit, extension),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

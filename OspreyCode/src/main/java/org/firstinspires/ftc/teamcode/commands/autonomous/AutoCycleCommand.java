package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.IterativeCommand;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
@Config
public class AutoCycleCommand extends SequentialCommandGroup {
    public static int AUTO_CYCLES = 5;
    public AutoCycleCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension, VisionSubsystem vision){
        super(new AutoCyclePreloadCommand(drive, deposit, extension, lift, vision),
                new IterativeCommand(i->
                    new AutoIntakeWarehouseCommand(drive, intake, lift, deposit, extension, i.length())
                        .andThen(new AutoDepositAllianceCommand(drive, intake, lift, deposit, extension)),
                        "",
                        "nnnn",
                        i->i+"n"),
                new AutoParkWarehouseCommand(drive, lift, deposit, extension),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

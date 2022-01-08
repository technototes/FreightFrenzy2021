package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.IterativeCommand;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
@Config
public class AutoCycleCommand extends SequentialCommandGroup {
    public static int MAX_AUTO_CYCLES = 7;
    public static int TIME_TO_STOP = 25;
    public AutoCycleCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension, VisionSubsystem vision){
        super(()->drive.setPoseEstimate(RobotConstants.CYCLE_START_SELECT.get()),
                ()->drive.distanceSensorLocalizer.setGyroOffset(-RobotConstants.CYCLE_START_SELECT.get().getHeading()),
                new AutoCyclePreloadCommand(drive, deposit, extension, lift, vision),
                new IterativeCommand(i->
                    new AutoIntakeWarehouseCommand(drive, intake, lift, deposit, extension, i)
                        .andThen(new AutoDepositAllianceCommand(drive, intake, lift, deposit, extension))
                            .onlyIf(()->CommandScheduler.getInstance().getOpModeRuntime()<TIME_TO_STOP),
                        MAX_AUTO_CYCLES),
                new AutoParkWarehouseCommand(drive, lift, deposit, extension),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

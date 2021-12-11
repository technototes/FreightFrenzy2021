package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutoDuckCommand extends SequentialCommandGroup {
    public AutoDuckCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension, VisionSubsystem vision, CarouselSubsystem carousel) {
        super(() -> drive.setPoseEstimate(RobotConstants.DUCK_START_SELECT.get()),
                new AutoDuckPreloadCommand(drive, deposit, extension, lift, vision),
                new AutoCarouselCommand(drive, lift, deposit, extension, carousel),
                new AutoIntakeDuckCommand(drive, intake),
                new AutoDepositDuckCommand(drive, deposit, extension, lift, intake),
                new AutoParkBarrierCommand(drive, lift, deposit, extension),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class AutonomousDuckCommand extends SequentialCommandGroup {
    public AutonomousDuckCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, DepositSubsystem deposit, VisionSubsystem vision, CarouselSubsystem carousel){
          super(new DepositDuckPreloadCommand(drive, deposit, lift, vision),
                  new ToCarouselCommand(drive, lift, deposit, carousel),
                  new IntakeDuckCommand(drive, intake),
                  new DepositDuckCommand(drive, deposit, lift, intake),
                  new ParkSquareCommand(drive, lift, deposit),
                  CommandScheduler.getInstance()::terminateOpMode);
    }  
}

package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.RelocalizeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
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

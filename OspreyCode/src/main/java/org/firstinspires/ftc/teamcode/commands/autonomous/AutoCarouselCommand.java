package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoCarouselCommand extends SequentialCommandGroup {
    public AutoCarouselCommand(DrivebaseSubsystem drive, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension, CarouselSubsystem carousel){
        super(new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_CAROUSEL)
                .alongWith(new DepositCollectCommand(deposit, extension, lift)),
                new CarouselSpinCommand(carousel).withTimeout(4));
    }
}

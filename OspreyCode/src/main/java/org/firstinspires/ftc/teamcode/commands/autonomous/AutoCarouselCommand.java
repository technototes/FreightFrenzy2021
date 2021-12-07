package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutoCarouselCommand extends SequentialCommandGroup {
    public AutoCarouselCommand(DrivebaseSubsystem drive, LiftSubsystem lift, DepositSubsystem deposit, ExtensionSubsystem extension, CarouselSubsystem carousel){
        super(new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_CAROUSEL)
                .alongWith(new ArmInCommand(deposit), new WaitCommand(0.5).andThen(new LiftCollectCommand(lift).withTimeout(1.5))),
                new CarouselSpinCommand(carousel).withTimeout(3));
    }
}

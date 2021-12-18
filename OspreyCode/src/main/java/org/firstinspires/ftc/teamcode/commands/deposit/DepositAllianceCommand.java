package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositAllianceCommand extends SequentialCommandGroup {
    public DepositAllianceCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(new WaitCommand(0.3).andThen(
                new LiftLevel3Command(lift).withTimeout(1),
            new WaitCommand(0.2).andThen(new ExtensionOutCommand(extension)),
//                RobotConstants.getAlliance().selectOf(
//                        new ExtensionLeftSideCommand(extension, OUT),
//                        new ExtensionRightSideCommand(extension, OUT)),
                new ArmAllianceCommand(arm)));
    }
}

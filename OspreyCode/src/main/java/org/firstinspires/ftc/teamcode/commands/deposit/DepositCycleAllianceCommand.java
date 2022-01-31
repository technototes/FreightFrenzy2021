package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositCycleAllianceCommand extends ParallelCommandGroup {
    public DepositCycleAllianceCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(
                new LiftLevel3Command(lift).withTimeout(1),
                //new WaitCommand(0).andThen(new ExtensionOutCommand(extension, RobotConstants.getAlliance().selectOf(ExtensionSubsystem.ExtensionConstants.AUTO_LEFT, ExtensionSubsystem.ExtensionConstants.AUTO_RIGHT))),
                new ExtensionOutCommand(extension),
                new ArmAllianceCommand(arm));
    }
}

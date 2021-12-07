package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.OUT;

public class DepositAllianceCommand extends ParallelCommandGroup {
    public DepositAllianceCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(new WaitCommand(0.3).andThen(new LiftLevel3Command(lift)),
                Alliance.Selector.selectOf(RobotConstants.getAlliance(),
                        new ExtensionLeftSideCommand(extension, OUT),
                        new ExtensionRightSideCommand(extension, OUT)),
                new ArmOutCommand(arm));
    }
}

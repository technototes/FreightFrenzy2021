package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftNeutralCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositSharedCommand extends ParallelCommandGroup {
    public DepositSharedCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(new LiftNeutralCommand(lift),
                Alliance.Selector.selectOf(RobotConstants.getAlliance(),
                        new ExtensionRightSideCommand(extension),
                        new ExtensionLeftSideCommand(extension)),
                new ArmOutCommand(arm));
    }
}

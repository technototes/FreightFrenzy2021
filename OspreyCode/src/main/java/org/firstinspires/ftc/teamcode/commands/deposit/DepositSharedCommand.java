package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositSharedCommand extends ParallelCommandGroup {
    public DepositSharedCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(new LiftSharedCommand(lift),
                Alliance.Selector.selectOf(RobotConstants.getAlliance(),
                        new ExtensionRightSideCommand(extension),
                        new ExtensionLeftSideCommand(extension)),
                new ArmAllianceCommand(arm));
    }
}

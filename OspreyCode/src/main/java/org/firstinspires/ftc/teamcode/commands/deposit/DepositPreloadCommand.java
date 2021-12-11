package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class DepositPreloadCommand extends ParallelCommandGroup {
    public DepositPreloadCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift, VisionSubsystem vision){
        super(new LiftBarcodeSelectCommand(lift, vision).withTimeout(1),
                new ArmAllianceCommand(arm),
                RobotConstants.getAlliance().selectOf(
                        new ExtensionLeftSideCommand(extension, ExtensionSubsystem.ExtensionConstants.OUT),
                        new ExtensionRightSideCommand(extension, ExtensionSubsystem.ExtensionConstants.OUT)));
    }
}

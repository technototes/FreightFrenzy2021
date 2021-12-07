package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class DepositPreloadCommand extends ParallelCommandGroup {
    public DepositPreloadCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift, VisionSubsystem vision){
        super(new LiftBarcodeSelectCommand(lift, vision),
                new ArmOutCommand(arm),
                new ExtensionOutCommand(extension));
    }
}

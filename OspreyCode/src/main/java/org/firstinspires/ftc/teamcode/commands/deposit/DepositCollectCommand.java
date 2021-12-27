package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.arm.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmRaiseInCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel1Command;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class DepositCollectCommand extends SequentialCommandGroup {
    public DepositCollectCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(new ArmRaiseInCommand(arm).alongWith(new ExtensionCollectCommand(extension), new WaitCommand(0.3).andThen(new LiftLevel1Command(lift).withTimeout(0.8))),
                new LiftCollectCommand(lift).withTimeout(0.3).alongWith(new ArmInCommand(arm)));
    }
}

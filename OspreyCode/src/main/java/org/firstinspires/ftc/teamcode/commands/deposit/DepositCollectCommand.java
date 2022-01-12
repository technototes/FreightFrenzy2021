package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
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

public class DepositCollectCommand extends ParallelCommandGroup {
    public DepositCollectCommand(ArmSubsystem arm, ExtensionSubsystem extension, LiftSubsystem lift){
        super(new WaitCommand(0.2).andThen(new ArmInCommand(arm)),
                new ExtensionCollectCommand(extension),
                new WaitCommand(0.2).andThen(new LiftLevel1Command(lift).deadline(new WaitCommand(0.5)).andThen(new LiftCollectCommand(lift).withTimeout(0.3))));
    }
}

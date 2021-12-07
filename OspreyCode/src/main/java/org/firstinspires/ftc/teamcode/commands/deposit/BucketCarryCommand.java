package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class BucketCarryCommand implements Command {
    public DepositSubsystem depositSubsystem;
    public BucketCarryCommand(DepositSubsystem s){
        depositSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        depositSubsystem.setDump(DepositSubsystem.DepositConstants.CARRY);
    }
}

package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class BucketCollectCommand implements Command {
    public DepositSubsystem depositSubsystem;
    public BucketCollectCommand(DepositSubsystem s){
        depositSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        depositSubsystem.setDump(DepositSubsystem.DepositConstants.COLLECT);
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.5;
    }
}

package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class BucketDumpCommand implements Command {
    public DepositSubsystem depositSubsystem;
    public BucketDumpCommand(DepositSubsystem s){
        depositSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        depositSubsystem.dump();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.2;
    }
}

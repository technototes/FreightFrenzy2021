package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class ArmDumpCommand implements Command {
    DepositSubsystem subsystem;
    public ArmDumpCommand(DepositSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.dump();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>1;
    }
}

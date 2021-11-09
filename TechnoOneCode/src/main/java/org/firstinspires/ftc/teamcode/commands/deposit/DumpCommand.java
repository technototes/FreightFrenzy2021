package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class DumpCommand extends DumpVariableCommand {
    public DumpCommand(DepositSubsystem s){
        super(s, DepositSubsystem.DepositConstants.AUTO_DUMP);
    }
    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.3;
    }
}

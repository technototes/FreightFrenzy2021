package org.firstinspires.ftc.teamcode.commands.deposit;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class CollectCommand extends DumpVariableCommand {
    public CollectCommand(DepositSubsystem s){
        super(s, DepositSubsystem.DepositConstants.COLLECT);
    }
    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.5;
    }
}

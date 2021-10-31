package org.firstinspires.ftc.teamcode.commands.deposit;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class CollectCommand extends DumpVariableCommand {
    public CollectCommand(DepositSubsystem s) {
        super(s, DepositSubsystem.DepositConstants.COLLECT); // yes very good
    }

    @Override
    public boolean isFinished() {
        return true; // ask alex about this
    }
}

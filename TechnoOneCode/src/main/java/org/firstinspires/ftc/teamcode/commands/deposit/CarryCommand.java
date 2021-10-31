package org.firstinspires.ftc.teamcode.commands.deposit;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class CarryCommand extends DumpVariableCommand {
    public CarryCommand(DepositSubsystem s){

        super(s, DepositSubsystem.DepositConstants.CARRY);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}

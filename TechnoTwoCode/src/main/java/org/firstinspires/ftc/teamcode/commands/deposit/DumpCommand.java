package org.firstinspires.ftc.teamcode.commands.deposit;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class DumpCommand extends DumpVariableCommand {
    // no member variables because idk its already extending idk
    public DumpCommand(DepositSubsystem s) {
        super(s, DepositSubsystem.DepositConstants.DUMP); // yes work
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.5;
    }
}
package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class ArmExtendCommand implements Command {
    DepositSubsystem subsystem;
    public ArmExtendCommand(DepositSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.fullyOut();
        subsystem.carry();
    }
    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>1;
    }
}

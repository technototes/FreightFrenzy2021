package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class ArmExtendCommand implements Command {
    public DepositSubsystem subsystem;
    public ArmExtendCommand(DepositSubsystem s){
        subsystem = s;
//        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.carry();
        if(getRuntime().seconds()>0.2) subsystem.fullyOut();

    }
    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.5;
    }
}

package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class ArmRaiseCommand implements Command {
    public DepositSubsystem subsystem;
    public ArmRaiseCommand(DepositSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.carry();
         subsystem.fullyUp();
    }


    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.5;
    }
}

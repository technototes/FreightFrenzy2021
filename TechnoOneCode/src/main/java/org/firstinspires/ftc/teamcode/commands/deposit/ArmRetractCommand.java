package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class ArmRetractCommand implements Command {
    public DepositSubsystem subsystem;
    public ArmRetractCommand(DepositSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.fullyIn();
        subsystem.collect();
    }
    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>1;
    }
}


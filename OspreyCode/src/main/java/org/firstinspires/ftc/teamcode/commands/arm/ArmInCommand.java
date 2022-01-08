package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmInCommand extends ArmCommand {
    public ArmInCommand(ArmSubsystem s){
        super(s);
    }

    @Override
    public void execute() {
        if(getRuntime().seconds()>0.2) subsystem.in();
        subsystem.collect();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.6;
    }
}


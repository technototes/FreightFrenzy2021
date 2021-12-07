package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmOutCommand implements Command {
    public ArmSubsystem subsystem;
    public ArmOutCommand(ArmSubsystem s){
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
        return getRuntime().seconds()>0.7;
    }
}


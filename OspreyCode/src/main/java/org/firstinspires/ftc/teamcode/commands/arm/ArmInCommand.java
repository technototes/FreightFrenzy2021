package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmInCommand implements Command {
    public ArmSubsystem subsystem;
    public ArmInCommand(ArmSubsystem s){
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
        return getRuntime().seconds()>0.5;
    }
}


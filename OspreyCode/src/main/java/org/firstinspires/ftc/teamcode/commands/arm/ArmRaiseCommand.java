package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmRaiseCommand implements Command {
    public ArmSubsystem subsystem;
    public ArmRaiseCommand(ArmSubsystem s){
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

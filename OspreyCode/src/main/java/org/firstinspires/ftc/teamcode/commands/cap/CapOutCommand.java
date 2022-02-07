
package org.firstinspires.ftc.teamcode.commands.cap;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;

public class CapOutCommand implements Command {
    public CapSubsystem subsystem;
    public CapOutCommand(CapSubsystem cap){
        subsystem = cap;
        addRequirements(cap);
    }

    @Override
    public void execute() {
        subsystem.raise();
        if(getRuntime().seconds()>0.5) subsystem.raise2();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() >1;
    }
}

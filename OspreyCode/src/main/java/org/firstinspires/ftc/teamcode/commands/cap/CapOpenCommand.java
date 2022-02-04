
package org.firstinspires.ftc.teamcode.commands.cap;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;

public class CapOpenCommand implements Command {
    public CapSubsystem subsystem;
    public CapOpenCommand(CapSubsystem cap){
        subsystem = cap;
        addRequirements(cap);
    }

    @Override
    public void execute() {
        subsystem.open();
    }

}

package org.firstinspires.ftc.teamcode.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeInCommand implements Command {
    IntakeSubsystem subsystem;

    public IntakeInCommand(IntakeSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.in();
    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.stop();
    }
}

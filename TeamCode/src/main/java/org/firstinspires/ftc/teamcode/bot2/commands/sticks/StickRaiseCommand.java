package org.firstinspires.ftc.teamcode.bot2.commands.sticks;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.StickSubsystem;

public class StickRaiseCommand implements Command {
    public StickSubsystem subsystem;
    public StickRaiseCommand(StickSubsystem sub){
        subsystem = sub;
    }

    @Override
    public void execute() {
        subsystem.raise();
    }
}

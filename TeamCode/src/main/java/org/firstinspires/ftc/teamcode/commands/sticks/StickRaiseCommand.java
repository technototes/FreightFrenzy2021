package org.firstinspires.ftc.teamcode.commands.sticks;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.StickSubsystem;

public class StickRaiseCommand extends Command {
    public StickSubsystem subsystem;
    public StickRaiseCommand(StickSubsystem sub){
        subsystem = sub;
    }

    @Override
    public void execute() {
        subsystem.raise();
    }
}

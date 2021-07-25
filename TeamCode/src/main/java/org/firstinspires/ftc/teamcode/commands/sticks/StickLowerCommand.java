package org.firstinspires.ftc.teamcode.commands.sticks;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.StickSubsystem;

public class StickLowerCommand extends Command {
    public StickSubsystem subsystem;
    public StickLowerCommand(StickSubsystem sub){
        subsystem = sub;
    }

    @Override
    public void execute() {
        subsystem.lower();
    }
}

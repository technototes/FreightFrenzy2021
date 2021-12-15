package org.firstinspires.ftc.teamcode.commands;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandGamepad;

public class RumbleTestCommand implements Command {
    CommandGamepad gamepad;

    public RumbleTestCommand(CommandGamepad gamepad){
        this.gamepad = gamepad;
    }

    @Override
    public void execute() {
        gamepad.rumble(10000);
    }
}

package org.firstinspires.ftc.teamcode.commands.cap;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ConditionalCommand;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;

public class CapTimeCommand extends CapCommand{
    public CapTimeCommand(CapSubsystem s){
        super(s, ()-> CommandScheduler.getInstance().getOpModeRuntime()<120 ? CapSubsystem.CapConstants.TOP : CapSubsystem.CapConstants.CARRY);
    }
}

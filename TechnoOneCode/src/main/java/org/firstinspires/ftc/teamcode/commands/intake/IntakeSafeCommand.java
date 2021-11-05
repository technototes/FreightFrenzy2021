package org.firstinspires.ftc.teamcode.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand implements Command {
    IntakeSubsystem subsystem;

    public IntakeSafeCommand(IntakeSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.in();
    }

    @Override
    public boolean isFinished() {
        return subsystem.isNearTarget();
    }

    @Override
    public void end(boolean cancel) {
        if(!cancel) subsystem.stop();
    }
}

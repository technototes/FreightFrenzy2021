package org.firstinspires.ftc.teamcode.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeOutCommand implements Command{
    IntakeSubsystem subsystem;
    public IntakeOutCommand(IntakeSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        if(getRuntime().seconds()%0.2 < 0.1) subsystem.out();
        else subsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.stop();
    }

}

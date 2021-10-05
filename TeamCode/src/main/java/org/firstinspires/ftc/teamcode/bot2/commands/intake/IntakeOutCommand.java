package org.firstinspires.ftc.teamcode.bot2.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.IntakeSubsystem;

public class IntakeOutCommand implements Command {
    public IntakeSubsystem subsystem;
    public IntakeOutCommand(IntakeSubsystem s) {
        subsystem = s;
        //this.addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.extake();
    }
}

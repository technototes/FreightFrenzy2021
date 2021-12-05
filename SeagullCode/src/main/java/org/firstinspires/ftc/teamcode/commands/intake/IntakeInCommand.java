package org.firstinspires.ftc.teamcode.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeInCommand implements Command {
    IntakeSubsystem intakeSubsystem;

    public IntakeInCommand(IntakeSubsystem s) {
        intakeSubsystem = s;
        addRequirements(s);
    }
    @Override
    public void execute() {
        intakeSubsystem.in();
    }
}

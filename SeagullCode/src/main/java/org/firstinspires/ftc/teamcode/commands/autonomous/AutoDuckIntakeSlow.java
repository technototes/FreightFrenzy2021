package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoDuckIntakeSlow implements Command {
    IntakeSubsystem subsystem;

    public AutoDuckIntakeSlow(IntakeSubsystem s) {
        subsystem = s;
        addRequirements(s);

    }
    @Override
    public void execute() { subsystem.slow();}

}

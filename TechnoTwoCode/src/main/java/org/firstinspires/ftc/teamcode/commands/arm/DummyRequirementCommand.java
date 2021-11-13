package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import java.util.function.DoubleSupplier;

public class DummyRequirementCommand implements Command {
    DumpSubsystem dump;

    public DummyRequirementCommand(DumpSubsystem a) {
        dump = a;
        addRequirements(a);
    }

    @Override
    public void execute() {
    }
}

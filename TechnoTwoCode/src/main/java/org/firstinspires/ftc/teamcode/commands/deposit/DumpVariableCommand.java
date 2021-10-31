package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

import java.util.function.DoubleSupplier;

public class DumpVariableCommand implements Command {
    public DepositSubsystem subsystem;
    public DoubleSupplier supplier;
    public DumpVariableCommand(DepositSubsystem s, DoubleSupplier pos) {
        subsystem = s;
        supplier = pos;
        addRequirements(s);
    }
    public DumpVariableCommand(DepositSubsystem s, double pos) {
        this(s, ()->pos);
    }
    @Override
    public void execute() {
        subsystem.setDump(supplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        if (cancel)
            subsystem.carry();
    }
}

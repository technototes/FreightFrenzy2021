package org.firstinspires.ftc.teamcode.commands.cap;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;

import java.util.function.DoubleSupplier;

public class CapCommand implements Command {
    public CapSubsystem subsystem;
    public DoubleSupplier supplier;
    public CapCommand(CapSubsystem s, DoubleSupplier pos){
        subsystem = s;
        supplier = pos;
        addRequirements(s);
    }
    public CapCommand(CapSubsystem s, double pos){
        this(s, ()->pos);
    }

    @Override
    public void execute() {
        subsystem.setCap(supplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>1;
    }

}

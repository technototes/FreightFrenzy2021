package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import java.util.function.DoubleSupplier;

public class ArmCommand implements Command {
    DumpSubsystem dump;
    DoubleSupplier doubleSupplier;

    public ArmCommand(DumpSubsystem a, DoubleSupplier d){
        dump = a;
        doubleSupplier = d;
        addRequirements(a);
    }

    public ArmCommand(DumpSubsystem a, double d){
        this(a, ()->d);
    }

    @Override
    public void init() {
        dump.setMotorPosition(doubleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return dump.isAtTarget();
    }

    @Override
    public void execute() {

    }
}

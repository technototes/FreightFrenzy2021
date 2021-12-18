package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import java.util.function.DoubleSupplier;

public class ArmCommand implements Command {
    DumpSubsystem dump;
    DoubleSupplier doubleSupplier;
    long startTime = 0;

    public ArmCommand(DumpSubsystem a, DoubleSupplier d){
        dump = a;
        doubleSupplier = d;
        addRequirements(a);
    }

    public ArmCommand(DumpSubsystem a, double d){
        this(a, ()->d);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        dump.setMotorPosition(doubleSupplier.getAsDouble());

    }

    @Override
    public boolean isFinished() {
        return dump.isAtTarget() || System.currentTimeMillis() > startTime+1000;
    }

    @Override
    public void execute() {

    }
}

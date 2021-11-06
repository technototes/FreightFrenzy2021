package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmCommand implements Command {
    ArmSubsystem arm;
    DoubleSupplier doubleSupplier;

    public ArmCommand(ArmSubsystem a, DoubleSupplier d){
        arm = a;
        doubleSupplier = d;
        addRequirements(a);
    }

    public ArmCommand(ArmSubsystem a, double d){
        this(a, ()->d);
    }

    @Override
    public void init() {
        arm.setPosition(doubleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return arm.isAtTarget();
    }

    @Override
    public void execute() {

    }
}

package org.firstinspires.ftc.teamcode.commands.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterSetSpeed2Command extends Command {
    public ShooterSubsystem subsystem;
    public DoubleSupplier supplier;
    public double curr;
    public ShooterSetSpeed2Command(ShooterSubsystem sub, DoubleSupplier sup){
//        addRequirements(sub);
        subsystem = sub;
        supplier = sup;
    }

    @Override
    public void init() {
        curr = supplier.getAsDouble();

    }

    @Override
    public void execute() {
        subsystem.setVelocity(curr*(1+commandRuntime.seconds()/2));
    }

    @Override
    public boolean isFinished() {
        return curr-subsystem.getVelocity() < 30;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.setVelocity(curr);
    }
}

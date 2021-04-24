package org.firstinspires.ftc.teamcode.commands.shooter;

import com.technototes.library.command.Command;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;
public class ShooterSetSpeedCommand extends Command {
    public ShooterSubsystem subsystem;
    public DoubleSupplier supplier;
    public double curr;
    public ShooterSetSpeedCommand(ShooterSubsystem sub, DoubleSupplier sup){
        //addRequirements(sub);
        subsystem = sub;
        supplier = sup;
    }

    @Override
    public void init() {
        curr = supplier.getAsDouble();

    }

    @Override
    public void execute() {
        subsystem.setVelocity(curr);
    }
}

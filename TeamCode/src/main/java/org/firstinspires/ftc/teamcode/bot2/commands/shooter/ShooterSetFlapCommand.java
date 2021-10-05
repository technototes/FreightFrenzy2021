package org.firstinspires.ftc.teamcode.bot2.commands.shooter;

import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterSetFlapCommand extends WaitCommand {
    public ShooterSubsystem subsystem;
    public DoubleSupplier supplier;
    public double curr;
    public ShooterSetFlapCommand(ShooterSubsystem sub, DoubleSupplier sup) {
        super(0.1);
        subsystem = sub;
        supplier = sup;
    }

    @Override
    public void init() {
        curr = supplier.getAsDouble();
        subsystem.setFlapPosition(curr);
    }

}

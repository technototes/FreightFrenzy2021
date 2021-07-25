package com.technototes.path.command;


import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import java.util.function.DoubleSupplier;

public class TurnCommand extends PathCommand {
    public DoubleSupplier supplier;
    public TurnCommand(MecanumDrivebaseSubsystem sub, double ang) {
        super(sub);
        supplier =()->ang;
    }
    public TurnCommand(MecanumDrivebaseSubsystem sub, DoubleSupplier ang) {
        super(sub);
        supplier = ang;
    }


    @Override
    public void init() {
        subsystem.turnAsync(Math.toRadians(supplier.getAsDouble())-subsystem.getExternalHeading());
    }

}

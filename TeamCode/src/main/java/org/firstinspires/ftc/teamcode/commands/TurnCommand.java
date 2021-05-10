package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.util.Angle;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.function.DoubleSupplier;

public class TurnCommand extends PathCommand {
    public DoubleSupplier supplier;
    public TurnCommand(DrivebaseSubsystem sub, double ang) {
        super(sub);
        supplier =()->ang;
    }
    public TurnCommand(DrivebaseSubsystem sub, DoubleSupplier ang) {
        super(sub);
        supplier = ang;
    }


    @Override
    public void init() {
        subsystem.turnAsync(Math.toRadians(supplier.getAsDouble())-subsystem.getExternalHeading());
    }

}

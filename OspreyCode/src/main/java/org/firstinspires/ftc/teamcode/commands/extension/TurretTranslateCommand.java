package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import java.util.function.BooleanSupplier;

public class TurretTranslateCommand extends ExtensionCommand{
public BooleanSupplier flipTranslate;
    public TurretTranslateCommand(ExtensionSubsystem subsystem, double turret, BooleanSupplier flip) {
        super(subsystem, 0, turret);
        flipTranslate = flip;
    }

    @Override
    public void initialize() {
        extensionSubsystem.translateTurret(flipTranslate.getAsBoolean() ? -turretTarget : turretTarget);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.01;
    }
}

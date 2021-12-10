package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

public class TurretTranslateCommand extends ExtensionCommand{

    public TurretTranslateCommand(ExtensionSubsystem subsystem, double turret) {
        super(subsystem, 0, turret);
    }

    @Override
    public void initialize() {
        extensionSubsystem.translateTurret(turretTarget);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.03;
    }
}

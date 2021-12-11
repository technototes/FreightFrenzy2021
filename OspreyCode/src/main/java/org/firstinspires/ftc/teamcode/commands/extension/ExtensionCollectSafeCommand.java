package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants;

public class ExtensionCollectSafeCommand extends ExtensionCommand{
    public ExtensionCollectSafeCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.IN, ExtensionConstants.CENTER);
    }


    @Override
    public void execute() {
        extensionSubsystem.setTurret(turretTarget);
        if(getRuntime().seconds()>0.3)extensionSubsystem.setSlide(slideTarget);
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.5;
    }
}

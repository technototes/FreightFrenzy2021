package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants;

public class ExtensionLeftSideCommand extends ExtensionOutCommand {
    public ExtensionLeftSideCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.MIDDLE, ExtensionConstants.LEFT);
    }
    public ExtensionLeftSideCommand(ExtensionSubsystem subsystem, double extension) {
        super(subsystem, extension, ExtensionConstants.LEFT);
    }
    @Override
    public void execute() {
        if(getRuntime().seconds()<0.5) extensionSubsystem.fullyOut();
        else{
            extensionSubsystem.setSlide(slideTarget);
            extensionSubsystem.setTurret(turretTarget);
        }
    }
}

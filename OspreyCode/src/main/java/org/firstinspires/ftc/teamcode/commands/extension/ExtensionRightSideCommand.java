package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants;

public class ExtensionRightSideCommand extends ExtensionOutCommand {
    public ExtensionRightSideCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.MIDDLE, ExtensionConstants.RIGHT);
    }
    public ExtensionRightSideCommand(ExtensionSubsystem subsystem, double extension) {
        super(subsystem, extension, ExtensionConstants.RIGHT);
    }

    @Override
    public void execute() {
        if(getRuntime().seconds()<0.7) extensionSubsystem.fullyOut();
        else{
            extensionSubsystem.setSlide(slideTarget);
            extensionSubsystem.setTurret(turretTarget);
        }
    }

}

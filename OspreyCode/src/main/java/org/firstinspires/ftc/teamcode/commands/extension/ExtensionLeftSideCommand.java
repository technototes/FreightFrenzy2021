package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants;

public class ExtensionLeftSideCommand extends ExtensionOutCommand {
    public ExtensionLeftSideCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.MIDDLE, ExtensionConstants.LEFT);
    }
}

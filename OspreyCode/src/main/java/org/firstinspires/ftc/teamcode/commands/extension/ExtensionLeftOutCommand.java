package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

public class ExtensionLeftOutCommand extends ExtensionLeftSideCommand{
    public ExtensionLeftOutCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionSubsystem.ExtensionConstants.OUT);
    }
}

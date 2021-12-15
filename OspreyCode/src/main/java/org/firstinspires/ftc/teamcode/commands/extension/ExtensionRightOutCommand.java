package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

public class ExtensionRightOutCommand extends ExtensionRightSideCommand{
    public ExtensionRightOutCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionSubsystem.ExtensionConstants.OUT);
    }
}

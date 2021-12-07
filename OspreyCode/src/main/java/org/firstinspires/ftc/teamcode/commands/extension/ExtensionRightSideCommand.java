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
}

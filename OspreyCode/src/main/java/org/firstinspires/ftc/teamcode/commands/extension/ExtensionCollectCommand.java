package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants;

public class ExtensionCollectCommand extends ExtensionCommand{
    public ExtensionCollectCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.IN, ExtensionConstants.CENTER);
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.4;
    }
}

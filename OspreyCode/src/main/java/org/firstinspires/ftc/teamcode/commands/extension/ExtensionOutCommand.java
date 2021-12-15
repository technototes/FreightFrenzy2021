package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants;

public class ExtensionOutCommand extends ExtensionCommand{
    public ExtensionOutCommand(ExtensionSubsystem subsystem, double turret) {
        super(subsystem, ExtensionConstants.OUT, turret);
    }
    public ExtensionOutCommand(ExtensionSubsystem subsystem, double slide, double turret) {
        super(subsystem, slide, turret);
    }

    public ExtensionOutCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.OUT, ExtensionConstants.CENTER);
    }



    @Override
    public void execute() {
        if(getRuntime().seconds()<0.7) extensionSubsystem.setSlide(ExtensionConstants.STEAL_SHARED);
        else{
            extensionSubsystem.setSlide(slideTarget);
            extensionSubsystem.setTurret(turretTarget);
        }
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>1;
    }
}

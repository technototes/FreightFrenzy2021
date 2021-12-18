package org.firstinspires.ftc.teamcode.commands.extension;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

public class ExtensionCommand implements Command {
    public ExtensionSubsystem extensionSubsystem;
    public double slideTarget, turretTarget;
    public ExtensionCommand(ExtensionSubsystem subsystem, double slide, double turret){
        extensionSubsystem = subsystem;
        addRequirements(subsystem);
        slideTarget = slide;
        turretTarget = turret;
    }
    @Override
    public void execute() {
        extensionSubsystem.setTurret(turretTarget);
        extensionSubsystem.setSlide(slideTarget);
    }
}

package org.firstinspires.ftc.teamcode.commands.extension;

import static org.firstinspires.ftc.teamcode.RobotConstants.SharedHubStrategy.OWN;
import static org.firstinspires.ftc.teamcode.RobotConstants.getAlliance;
import static org.firstinspires.ftc.teamcode.RobotConstants.getSharedStrategy;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.CENTER;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.LEFT;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.MIDDLE;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.RIGHT;

import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

public class ExtensionSideCommand extends ExtensionCommand {
    public ExtensionSideCommand(ExtensionSubsystem subsystem) {
        super(subsystem, getSharedStrategy() == OWN ? MIDDLE : CENTER, getAlliance() == Alliance.RED ? RIGHT : LEFT);
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

package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class RelocalizeCycleCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public RelocalizeCycleCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.relocalizeCyclePose(AutonomousConstants.ALLIANCE);
    }

}

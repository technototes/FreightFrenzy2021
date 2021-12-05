package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class RelocalizeDuckCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public RelocalizeDuckCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.relocalizeDuckPose(AutonomousConstants.getAlliance());
    }
}

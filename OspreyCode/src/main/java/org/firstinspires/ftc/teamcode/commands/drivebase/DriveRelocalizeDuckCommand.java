package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class DriveRelocalizeDuckCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public DriveRelocalizeDuckCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.relocalizeDuckPose(RobotConstants.getAlliance());
    }
}
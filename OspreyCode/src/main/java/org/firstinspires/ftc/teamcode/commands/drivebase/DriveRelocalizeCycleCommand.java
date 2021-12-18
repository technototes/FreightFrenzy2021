package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class DriveRelocalizeCycleCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public DriveRelocalizeCycleCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.relocalizeCyclePose(RobotConstants.getAlliance());
    }

}

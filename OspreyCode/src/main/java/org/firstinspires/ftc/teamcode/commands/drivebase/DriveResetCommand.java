package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;


public class DriveResetCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public DriveResetCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.resetGyro();
        subsystem.setExternalHeading(RobotConstants.getAlliance().selectOf(-Math.PI/2, Math.PI/2));
    }
}

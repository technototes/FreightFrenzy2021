package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;


public class ResetGyroCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public ResetGyroCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.setExternalHeading(0);
    }
}
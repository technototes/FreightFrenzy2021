package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class RelocalizeCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public RelocalizeCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {

    }
}

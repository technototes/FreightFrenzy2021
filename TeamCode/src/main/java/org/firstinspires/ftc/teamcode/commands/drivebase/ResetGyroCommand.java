package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class ResetGyroCommand extends Command {
    public DrivebaseSubsystem subsystem;
    public ResetGyroCommand(DrivebaseSubsystem s){
        subsystem = s;
        //addRequirements(s.dummySubsystem);
    }

    @Override
    public void init() {
        subsystem.setExternalHeading(0);
    }
}

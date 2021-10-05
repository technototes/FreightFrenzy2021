package org.firstinspires.ftc.teamcode.bot2.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;

public class ResetGyroCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public ResetGyroCommand(DrivebaseSubsystem s){
        subsystem = s;
        //addRequirements(s.dummySubsystem);
    }

    @Override
    public void init() {
        subsystem.setExternalHeading(0);
    }

    @Override
    public void execute() {

    }
}

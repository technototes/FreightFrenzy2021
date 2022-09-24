package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class DriveToCenterCommand implements Command {
    public DriveToCenterCommand(DrivebaseSubsystem drive) {
        addRequirements(drive);
    }

    @Override
    public void execute() {

    }
}

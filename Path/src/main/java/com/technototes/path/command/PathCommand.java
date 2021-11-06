package com.technototes.path.command;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


public class PathCommand implements Command {
    public MecanumDrivebaseSubsystem subsystem;


    public PathCommand(MecanumDrivebaseSubsystem sub){
        addRequirements(sub);
        subsystem = sub;
    }


    @Override
    public void execute() {
        subsystem.update();
    }

    @Override
    public boolean isFinished() {
        return !subsystem.isBusy();
    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.setDriveSignal(new DriveSignal());
    }

    public PathCommand path(PathCommand c){
        CommandScheduler.getInstance().scheduleAfterOther(this, c);
        return c;
    }
}

package com.technototes.path.command;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


public class PathCommand extends Command {
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
        subsystem.setDriveSignal(new DriveSignal());
    }

    public PathCommand turn(double degrees){
        return path(new TurnCommand(subsystem, degrees));
    }

    public PathCommand spline(double x, double y, double end, double rot){
        return path(new SplineCommand(subsystem, x, y, end, rot));
    }

    public PathCommand strafe(double x, double y, double rot){
        return path(new StrafeCommand(subsystem, x, y, rot));
    }

    public PathCommand path(PathCommand c){
        CommandScheduler.getInstance().scheduleAfterOther(this, c);
        return c;
    }
}

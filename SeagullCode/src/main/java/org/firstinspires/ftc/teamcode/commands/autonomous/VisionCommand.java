package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.DuckOrDepot;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class VisionCommand implements Command {
    public VisionSubsystem subsystem;
    public VisionCommand(VisionSubsystem s, Alliance alliance, DuckOrDepot side){
        subsystem = s;
        subsystem.setStartingPosition(alliance, side);
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.startBarcodePipeline();
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.stopBarcodePipeline();
    }
}

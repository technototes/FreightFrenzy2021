package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class VisionCommand implements Command {
    private VisionSubsystem subsystem;
    public VisionCommand(VisionSubsystem s){
        subsystem = s;
        addRequirements(s);
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

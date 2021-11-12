package org.firstinspires.ftc.teamcode.commands.bucket;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

public class BucketTestComand implements Command {
    private BucketSubsystem subsystem;
    public BucketTestComand(BucketSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.setPositionCombination(1.0, 1.0);
    }
}

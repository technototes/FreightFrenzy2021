package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class BucketDumpCommand implements Command {
    public ArmSubsystem depositSubsystem;
    public BucketDumpCommand(ArmSubsystem s){
        depositSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        depositSubsystem.dump();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.1;
    }
}

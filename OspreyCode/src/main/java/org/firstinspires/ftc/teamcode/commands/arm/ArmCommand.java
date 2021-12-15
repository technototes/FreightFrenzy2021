package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public abstract class ArmCommand implements Command {
    public ArmSubsystem subsystem;
    public ArmCommand(ArmSubsystem arm){
        subsystem = arm;
        addRequirements(arm);
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.5;
    }
}

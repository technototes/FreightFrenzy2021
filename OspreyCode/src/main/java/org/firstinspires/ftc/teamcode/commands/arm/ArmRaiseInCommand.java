package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmRaiseInCommand extends ArmCommand {
    public ArmRaiseInCommand(ArmSubsystem s){
        super(s);
    }

    @Override
    public void execute() {
        subsystem.fakeCarry();
         subsystem.up();
    }


    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.3;
    }
}

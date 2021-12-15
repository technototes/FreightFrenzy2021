package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmSharedCommand extends ArmCommand {
    public ArmSharedCommand(ArmSubsystem s){
         super(s);
    }

    @Override
    public void execute() {
        subsystem.down();
        subsystem.fakeCarry();
    }

    @Override
    public void end(boolean cancel) {
        subsystem.carry();
    }
}


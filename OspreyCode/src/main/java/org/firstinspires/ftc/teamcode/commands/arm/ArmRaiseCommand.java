package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmRaiseCommand extends ArmCommand {
    public ArmRaiseCommand(ArmSubsystem s){
        super(s);
    }

    @Override
    public void execute() {
        subsystem.carry();
         subsystem.up();
    }

}

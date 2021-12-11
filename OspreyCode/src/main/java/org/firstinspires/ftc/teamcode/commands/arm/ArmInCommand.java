package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmInCommand extends ArmCommand {
    public ArmInCommand(ArmSubsystem s){
        super(s);
    }

    @Override
    public void execute() {
        subsystem.in();
        subsystem.collect();
    }

}


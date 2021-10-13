package org.firstinspires.ftc.teamcode.commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmExtendCommand implements Command {
    ArmSubsystem subsystem;
    public ArmExtendCommand(ArmSubsystem a){
        subsystem = a;
    }
    @Override
    public void execute() {

    }
}

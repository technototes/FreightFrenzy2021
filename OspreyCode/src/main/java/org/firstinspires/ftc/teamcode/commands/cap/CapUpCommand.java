package org.firstinspires.ftc.teamcode.commands.cap;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;

public class CapUpCommand extends CapCommand{
    public CapUpCommand(CapSubsystem cap){
        super(cap, CapSubsystem.CapConstants.TOP);
    }
}

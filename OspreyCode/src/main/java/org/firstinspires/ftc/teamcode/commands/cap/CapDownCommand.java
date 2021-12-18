package org.firstinspires.ftc.teamcode.commands.cap;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class CapDownCommand extends CapCommand{
    public CapDownCommand(CapSubsystem cap, LiftSubsystem lift){
        super(cap, ()->lift.isLifted() ? CapSubsystem.CapConstants.CARRY : CapSubsystem.CapConstants.COLLECT);
    }
    public CapDownCommand(CapSubsystem cap){
        super(cap, CapSubsystem.CapConstants.CARRY);
    }


    @Override
    public boolean isFinished() {
        super.isFinished();
        return false;
    }
}

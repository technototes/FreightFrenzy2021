package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftUpCommand extends LiftCommand{
    public LiftUpCommand(LiftSubsystem l){
        super(l, LiftSubsystem.LIFT_UPPER_LIMIT);
    }
}

package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftUpCommand extends LiftCommand{
    public LiftUpCommand(LiftSubsystem l){
        super(l, LiftSubsystem.LiftConstants.LIFT_UPPER_LIMIT);
    }
}

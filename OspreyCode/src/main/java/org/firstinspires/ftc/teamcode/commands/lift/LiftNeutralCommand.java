package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftNeutralCommand extends LiftCommand {
    public LiftNeutralCommand(LiftSubsystem l){
        super(l, LiftSubsystem.LiftConstants.NEUTRAL);
    }
}

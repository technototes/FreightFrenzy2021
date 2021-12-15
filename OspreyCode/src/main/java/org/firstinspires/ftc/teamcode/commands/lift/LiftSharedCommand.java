package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftConstants;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftSharedCommand extends LiftCommand {
    public LiftSharedCommand(LiftSubsystem l){
        super(l, LiftConstants.NEUTRAL);
    }
}

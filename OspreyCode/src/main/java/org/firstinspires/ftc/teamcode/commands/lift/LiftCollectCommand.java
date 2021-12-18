package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftConstants;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftCollectCommand extends LiftCommand {
    public LiftCollectCommand(LiftSubsystem l){
        super(l, LiftConstants.COLLECT);
    }
}

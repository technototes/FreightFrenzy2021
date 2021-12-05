package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftLevel2Command extends LiftCommand {
    public LiftLevel2Command(LiftSubsystem l){
        super(l, LiftSubsystem.LiftConstants.LEVEL_2);
    }
}

package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftLevel2TeleCommand extends LiftCommand {
    public LiftLevel2TeleCommand(LiftSubsystem l){
        super(l, LiftSubsystem.LiftConstants.TELEOP_LEVEL_2);
    }
}

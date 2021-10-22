package org.firstinspires.ftc.teamcode.commands.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftDownCommand extends LiftCommand {
    public LiftDownCommand(LiftSubsystem l){
        super(l, LiftSubsystem.LiftConstants.LIFT_LOWER_LIMIT);
    }
}

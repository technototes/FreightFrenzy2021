package org.firstinspires.ftc.teamcode.commands.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterStopCommand extends Command {
    public ShooterSubsystem subsystem;
    public ShooterStopCommand(ShooterSubsystem sub){
        addRequirements(sub);
        subsystem = sub;
    }


    @Override
    public void init() {
        subsystem.setVelocity(0);
    }

}

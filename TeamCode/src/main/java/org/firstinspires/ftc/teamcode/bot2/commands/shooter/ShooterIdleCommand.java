package org.firstinspires.ftc.teamcode.bot2.commands.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;

public class ShooterIdleCommand implements Command {
    public ShooterSubsystem subsystem;
    public ShooterIdleCommand(ShooterSubsystem s){
        addRequirements(s);
        subsystem = s;
    }

    @Override
    public void init() {
        subsystem.setVelocity(subsystem.getIdleVelocity());
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return subsystem.isAtIdleVelocity();
    }
}

package org.firstinspires.ftc.teamcode.commands.shooter;

import com.technototes.library.command.Command;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterStopCommand extends WaitCommand {
    public ShooterSubsystem subsystem;
    public ShooterStopCommand(ShooterSubsystem sub){
        super(1);
        addRequirements(sub);
        subsystem = sub;
    }


    @Override
    public void execute() {
        subsystem.setVelocity(0);
    }

}

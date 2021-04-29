package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

import java.util.function.BooleanSupplier;

public class WobbleRotateLeftCommand extends Command {

    public WobbleSubsystem subsystem;
    public double startingPos;
    public BooleanSupplier runCondition;

    public WobbleRotateLeftCommand(WobbleSubsystem sub){
        addRequirements(sub);
        subsystem = sub;
    }

    @Override
    public void init() {
        startingPos = subsystem.getTurretPosition();
    }

    @Override
    public void execute() {
        subsystem.setTurretPosition(startingPos-commandRuntime.seconds()/5);
    }
}

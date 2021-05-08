package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.Command;
import com.technototes.library.control.gamepad.CommandButton;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

import java.util.function.BooleanSupplier;

public class WobbleRotateRightCommand extends Command {

    public WobbleSubsystem subsystem;
    public double startingPos;
    public BooleanSupplier runCondition;

    public WobbleRotateRightCommand(WobbleSubsystem sub, CommandButton b){
        subsystem = sub;
        runCondition = b::isReleased;

    }
    public WobbleRotateRightCommand(WobbleSubsystem sub){
        subsystem = sub;
        runCondition = ()-> commandRuntime.seconds()>0.5;

    }

    @Override
    public void init() {
        startingPos = subsystem.getTurretPosition();
    }

    @Override
    public void execute() {
        subsystem.setTurretPosition(startingPos+commandRuntime.seconds()*2);
    }

    @Override
    public boolean isFinished() {
        return runCondition.getAsBoolean();
    }
}

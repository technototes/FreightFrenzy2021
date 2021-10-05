package org.firstinspires.ftc.teamcode.bot2.commands.wobble;

import com.technototes.library.command.Command;
import com.technototes.library.control.gamepad.CommandButton;

import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

import java.util.function.BooleanSupplier;

public class WobbleRotateLeftCommand implements Command {

    public WobbleSubsystem subsystem;
    public double startingPos;
    public BooleanSupplier runCondition;

    public WobbleRotateLeftCommand(WobbleSubsystem sub, CommandButton b){
        subsystem = sub;
        runCondition = b::isReleased;
    }
    public WobbleRotateLeftCommand(WobbleSubsystem sub){
        subsystem = sub;
        runCondition = ()->getRuntime().seconds()>0.5;
    }

    @Override
    public void init() {
        startingPos = subsystem.getTurretPosition();
    }

    @Override
    public void execute() {
        subsystem.setTurretPosition(startingPos-getRuntime().seconds()*2);
    }
    @Override
    public boolean isFinished() {
        return runCondition.getAsBoolean();
    }
}

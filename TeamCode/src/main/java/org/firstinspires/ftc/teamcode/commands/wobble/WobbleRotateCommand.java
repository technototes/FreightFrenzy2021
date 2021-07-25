package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.Command;
import com.technototes.library.control.gamepad.CommandButton;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class WobbleRotateCommand extends Command {

    public WobbleSubsystem subsystem;
    public DoubleSupplier pos;

    public WobbleRotateCommand(WobbleSubsystem sub, double p){
        this(sub, ()->p);
    }
    public WobbleRotateCommand(WobbleSubsystem sub, DoubleSupplier p){
        subsystem = sub;
        pos = p;
    }


    @Override
    public void execute() {
        subsystem.setTurretPosition(pos.getAsDouble());
    }

}

package org.firstinspires.ftc.teamcode.commands.turret;

import com.technototes.library.command.Command;
import com.technototes.library.control.gamepad.CommandButton;

import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

import java.util.function.DoubleSupplier;

public class TurretRotateCommand extends Command {

    public TurretSubsystem subsystem;
    public DoubleSupplier pos;

    public TurretRotateCommand(TurretSubsystem sub, double p){
        this(sub, ()->p);
    }
    public TurretRotateCommand(TurretSubsystem sub, DoubleSupplier p){
        subsystem = sub;
        pos = p;
    }

    @Override
    public void execute() {
        subsystem.setTurretPosition(pos.getAsDouble());
    }

}

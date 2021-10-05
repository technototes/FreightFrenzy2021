package org.firstinspires.ftc.teamcode.bot2.commands.turret;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.TurretSubsystem;

import java.util.function.DoubleSupplier;

public class TurretRotateCommand implements Command {

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

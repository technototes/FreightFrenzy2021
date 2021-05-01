package org.firstinspires.ftc.teamcode.commands.turret;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

import java.util.function.BooleanSupplier;

public class TurretRotateRightCommand extends Command {

    public TurretSubsystem subsystem;
    public double startingPos;
    public BooleanSupplier runCondition;

    public TurretRotateRightCommand(TurretSubsystem sub){
        addRequirements(sub);
        subsystem = sub;
    }

    @Override
    public void init() {
        startingPos = subsystem.getTurretPosition();
    }

    @Override
    public void execute() {
        subsystem.setTurretPosition(startingPos+Math.pow(commandRuntime.seconds()/5,3));
    }
}

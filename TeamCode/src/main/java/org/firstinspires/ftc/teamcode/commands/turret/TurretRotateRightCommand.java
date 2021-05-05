package org.firstinspires.ftc.teamcode.commands.turret;

import com.technototes.library.command.Command;
import com.technototes.library.control.gamepad.CommandButton;

import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

import java.util.function.BooleanSupplier;

public class TurretRotateRightCommand extends Command {

    public TurretSubsystem subsystem;
    public double startingPos;
    public CommandButton runCondition;

    public TurretRotateRightCommand(TurretSubsystem sub, CommandButton b){
        subsystem = sub;
        runCondition = b;
    }

    @Override
    public void init() {
        startingPos = subsystem.getTurretPosition();
    }

    @Override
    public void execute() {
        subsystem.setTurretPosition(startingPos+Math.pow(commandRuntime.seconds()/5,3));
    }

    @Override
    public boolean isFinished() {
        return runCondition.isReleased();
    }
}

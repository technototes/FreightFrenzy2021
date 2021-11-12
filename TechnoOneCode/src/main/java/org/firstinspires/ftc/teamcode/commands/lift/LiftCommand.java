package org.firstinspires.ftc.teamcode.commands.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

import java.util.function.DoubleSupplier;

public class LiftCommand implements Command {
    public LiftSubsystem liftSys;
    public DoubleSupplier doubleSupplier;
    public LiftCommand(LiftSubsystem ls, DoubleSupplier ds){
        liftSys = ls;
        doubleSupplier = ds;
        addRequirements(ls);
    }
    public LiftCommand(LiftSubsystem ls, double ds){
        this(ls, ()->ds);
    }


    @Override
    public void execute() {
        liftSys.setLiftPosition(doubleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return liftSys.isAtTarget();
    }

}

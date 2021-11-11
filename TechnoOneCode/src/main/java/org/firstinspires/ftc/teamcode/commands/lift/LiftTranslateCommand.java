package org.firstinspires.ftc.teamcode.commands.lift;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftTranslateCommand extends LiftCommand {
    public LiftTranslateCommand(LiftSubsystem ls, double amt) {
        super(ls, amt);
    }

    @Override
    public void init() {
        liftSys.setLiftPosition(liftSys.get()+doubleSupplier.getAsDouble());
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() && getRuntime().seconds()>0.1;
    }
}

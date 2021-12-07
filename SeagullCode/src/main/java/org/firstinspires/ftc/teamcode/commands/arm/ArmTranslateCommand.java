package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class ArmTranslateCommand extends ArmCommand{
    public ArmTranslateCommand(DumpSubsystem arm, double amt){
        super(arm,amt);
    }

    @Override
    public void initialize() {
        dump.setMotorPosition(dump.get()+doubleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() && getRuntime().seconds() > 0.1;
    }
}

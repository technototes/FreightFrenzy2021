package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmTranslateCommand extends ArmCommand{
    public ArmTranslateCommand(ArmSubsystem arm, double amt){
        super(arm,amt);
    }

    @Override
    public void init() {
        arm.setPosition(arm.get()+doubleSupplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() && getRuntime().seconds() > 0.1;
    }
}

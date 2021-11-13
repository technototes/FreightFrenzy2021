package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmCollectedCommand extends ArmCommand{
    public ArmCollectedCommand(ArmSubsystem arm){
        super(arm,0);
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

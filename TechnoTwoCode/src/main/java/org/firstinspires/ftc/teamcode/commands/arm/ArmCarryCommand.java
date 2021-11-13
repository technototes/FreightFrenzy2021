package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmCarryCommand extends ArmCommand{
    public ArmCarryCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.COLLECT);
    }
}

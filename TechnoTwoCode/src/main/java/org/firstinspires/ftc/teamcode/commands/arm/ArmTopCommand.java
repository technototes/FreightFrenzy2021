package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmTopCommand extends ArmCommand{
    public ArmTopCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.LIFT_UPPER_LIMIT);
    }
}

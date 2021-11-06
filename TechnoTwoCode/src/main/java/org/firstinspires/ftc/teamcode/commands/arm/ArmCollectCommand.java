package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmCollectCommand extends ArmCommand{
    public ArmCollectCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.COLLECT);
    }
}

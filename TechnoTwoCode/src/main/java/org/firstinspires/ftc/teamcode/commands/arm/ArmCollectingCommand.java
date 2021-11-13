package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmCollectingCommand extends ArmCommand{
    public ArmCollectingCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.COLLECT);
    }
}

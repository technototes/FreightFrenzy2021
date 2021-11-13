package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmBottomLevelCommand extends ArmCommand{
    public ArmBottomLevelCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.BOTTOM_LEVEL);
    }
}

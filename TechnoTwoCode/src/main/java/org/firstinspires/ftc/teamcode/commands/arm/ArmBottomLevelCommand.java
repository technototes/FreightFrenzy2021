package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class ArmBottomLevelCommand extends ArmCommand{
    public ArmBottomLevelCommand(DumpSubsystem arm){
        super(arm,DumpSubsystem.ArmConstant.ARM_BOTTOM_LEVEL);
    }
}

package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class ArmMiddleLevelCommand extends ArmCommand{
    public ArmMiddleLevelCommand(DumpSubsystem arm){
        super(arm, DumpSubsystem.ArmConstant.ARM_MIDDLE_LEVEL);
    }
}

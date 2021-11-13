package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmMiddleLevelCommand extends ArmCommand{
    public ArmMiddleLevelCommand(ArmSubsystem arm){
        super(arm, ArmSubsystem.ArmConstant.MIDDLE_LEVEL);
    }
}

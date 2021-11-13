package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmTopLevelCommand extends ArmCommand{
    public ArmTopLevelCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.LEVEL_ONE);
    }
}

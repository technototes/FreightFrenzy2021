package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmLevel3Command extends ArmCommand{
    public ArmLevel3Command(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.LEVEL_THREE);
    }
}

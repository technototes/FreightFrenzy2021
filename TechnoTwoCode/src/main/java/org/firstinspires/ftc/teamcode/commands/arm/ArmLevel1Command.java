package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmLevel1Command extends ArmCommand{
    public ArmLevel1Command(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.LEVEL_ONE);
    }
}

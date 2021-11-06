package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmLevel2Command extends ArmCommand{
    public ArmLevel2Command(ArmSubsystem arm){
        super(arm, ArmSubsystem.ArmConstant.LEVEL_TWO);
    }
}

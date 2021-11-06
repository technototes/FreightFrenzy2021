package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmBotCommand extends ArmCommand{
    public ArmBotCommand(ArmSubsystem arm){
        super(arm,ArmSubsystem.ArmConstant.LIFT_LOWER_LIMIT);
    }
}

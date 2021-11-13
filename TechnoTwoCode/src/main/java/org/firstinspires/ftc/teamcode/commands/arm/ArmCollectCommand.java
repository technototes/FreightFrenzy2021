package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class ArmCollectCommand extends ArmCommand{
    public ArmCollectCommand(DumpSubsystem arm){
        super(arm, DumpSubsystem.ArmConstant.COLLECT);
    }
}

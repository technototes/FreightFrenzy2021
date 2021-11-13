package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.TOP_LEVEL;

public class ArmTopLevelCommand extends ArmCommand{
    public ArmTopLevelCommand(DumpSubsystem arm){
        super(arm,TOP_LEVEL);
    }
}

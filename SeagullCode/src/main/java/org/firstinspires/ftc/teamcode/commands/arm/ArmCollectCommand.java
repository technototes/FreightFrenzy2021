package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_COLLECT;

public class ArmCollectCommand extends ArmCommand{
    public ArmCollectCommand(DumpSubsystem arm){
        super(arm, ARM_COLLECT);
    }
}

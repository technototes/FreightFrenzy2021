package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.COMBINATION_CARRY;

public class BucketCarryCommand extends BucketCommand{
    public BucketCarryCommand(DumpSubsystem bs) {
        super(bs, COMBINATION_CARRY);
    }
}

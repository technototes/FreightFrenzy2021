package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.BUCKET_CARRY;

public class BucketCarryCommand extends BucketCommand{
    public BucketCarryCommand(DumpSubsystem bs) {
        super(bs, BUCKET_CARRY);
    }
}

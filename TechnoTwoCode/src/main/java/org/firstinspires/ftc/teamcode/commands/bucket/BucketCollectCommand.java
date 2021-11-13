package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.BUCKET_COLLECT;

public class BucketCollectCommand extends BucketCommand{
    public BucketCollectCommand(DumpSubsystem bs) {
        super(bs, BUCKET_COLLECT);
    }
}

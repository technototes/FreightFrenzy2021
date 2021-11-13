package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.BUCKET_TOP_LEVEL;

public class BucketUnloadTopLevelCommand extends BucketCommand{
    public BucketUnloadTopLevelCommand(DumpSubsystem bs) {
        super(bs, BUCKET_TOP_LEVEL);
    }
}

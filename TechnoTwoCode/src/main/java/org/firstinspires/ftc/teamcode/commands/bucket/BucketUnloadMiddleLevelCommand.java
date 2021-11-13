package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.BUCKET_MIDDLE_LEVEL;

public class BucketUnloadMiddleLevelCommand extends BucketCommand {
    public BucketUnloadMiddleLevelCommand(DumpSubsystem bs) {
        super(bs, BUCKET_MIDDLE_LEVEL);
    }
}

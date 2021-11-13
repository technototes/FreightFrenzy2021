package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.COMBINATION_MIDDLE_LEVEL;

public class BucketUnloadMiddleLevelCommand extends BucketCommand{
    public BucketUnloadMiddleLevelCommand(DumpSubsystem bs) {
        super(bs, COMBINATION_MIDDLE_LEVEL);
    }
}

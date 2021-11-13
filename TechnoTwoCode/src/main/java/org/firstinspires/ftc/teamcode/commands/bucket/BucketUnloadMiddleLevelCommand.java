package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_MIDDLE_LEVEL;

public class BucketUnloadMiddleLevelCommand extends BucketCommand{
    public BucketUnloadMiddleLevelCommand(BucketSubsystem bs) {
        super(bs, COMBINATION_MIDDLE_LEVEL);
    }
}

package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_LEVEL2;

public class BucketUnloadLevel2Command extends BucketCommand{
    public BucketUnloadLevel2Command(BucketSubsystem bs) {
        super(bs, COMBINATION_LEVEL2);
    }
}

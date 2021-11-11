package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_LEVEL1;

public class BucketUnloadLevel1Command extends BucketCommand{
    public BucketUnloadLevel1Command(BucketSubsystem bs) {
        super(bs, COMBINATION_LEVEL1);
    }
}

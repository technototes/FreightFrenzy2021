package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_LEVEL3;

public class BucketUnloadLevel3Command extends BucketCommand{

    public BucketUnloadLevel3Command(BucketSubsystem bs) {
        super(bs, COMBINATION_LEVEL3);
    }
}

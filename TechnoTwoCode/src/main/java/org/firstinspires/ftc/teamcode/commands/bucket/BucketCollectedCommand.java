package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_COLLECTED;

public class BucketCollectedCommand extends BucketCommand{
    public BucketCollectedCommand(BucketSubsystem bs) {
        super(bs, COMBINATION_COLLECTED);
    }
}

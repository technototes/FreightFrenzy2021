package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_COLLECTING;

public class BucketCollectingCommand extends BucketCommand{
    public BucketCollectingCommand(BucketSubsystem bs) {
        super(bs, COMBINATION_COLLECTING);
    }
}

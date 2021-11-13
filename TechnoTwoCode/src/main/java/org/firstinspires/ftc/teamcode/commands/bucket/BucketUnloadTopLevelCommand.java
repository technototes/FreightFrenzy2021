package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_TOP_LEVEL;

public class BucketUnloadTopLevelCommand extends BucketCommand{
    public BucketUnloadTopLevelCommand(BucketSubsystem bs) {
        super(bs, COMBINATION_TOP_LEVEL);
    }
}

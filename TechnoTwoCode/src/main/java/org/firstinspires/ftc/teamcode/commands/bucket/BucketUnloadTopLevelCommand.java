package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.COMBINATION_BOTTOM_LEVEL;

public class BucketUnloadTopLevelCommand extends BucketCommand{
    public BucketUnloadTopLevelCommand(BucketSubsystem bs) {
        super(bs, COMBINATION_BOTTOM_LEVEL);
    }
}

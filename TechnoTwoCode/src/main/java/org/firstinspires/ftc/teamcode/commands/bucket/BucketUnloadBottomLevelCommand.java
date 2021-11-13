package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.COMBINATION_BOTTOM_LEVEL;

public class BucketUnloadBottomLevelCommand extends BucketCommand{

    public BucketUnloadBottomLevelCommand(DumpSubsystem bs) {
        super(bs, COMBINATION_BOTTOM_LEVEL);
    }
}

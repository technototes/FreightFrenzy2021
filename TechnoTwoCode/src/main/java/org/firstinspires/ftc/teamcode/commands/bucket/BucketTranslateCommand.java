package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class BucketTranslateCommand extends BucketCommand{

    public BucketTranslateCommand(DumpSubsystem bs, double servo_targetPosition) {
        super(bs, servo_targetPosition);
    }
}

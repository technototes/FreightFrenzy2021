package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class BucketTranslateCommand extends BucketCommand{

    public BucketTranslateCommand(DumpSubsystem bs, double motor_targetPosition, double servo_targetPosition) {
        super(bs, motor_targetPosition, servo_targetPosition);
    }
}

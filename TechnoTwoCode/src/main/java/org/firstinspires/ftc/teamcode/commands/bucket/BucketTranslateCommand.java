package org.firstinspires.ftc.teamcode.commands.bucket;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

public class BucketTranslateCommand extends BucketCommand{

    public BucketTranslateCommand(BucketSubsystem bs, double motor_targetPosition, double servo_targetPosition) {
        super(bs, motor_targetPosition, servo_targetPosition);
    }
}

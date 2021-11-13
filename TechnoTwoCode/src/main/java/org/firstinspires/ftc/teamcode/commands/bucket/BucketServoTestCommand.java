package org.firstinspires.ftc.teamcode.commands.bucket;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

public class BucketServoTestCommand extends SequentialCommandGroup {
    private BucketSubsystem subsystem;
    public BucketServoTestCommand(BucketSubsystem s){
        super(new BucketTestCommand(s), new BucketTestCommand2(s), CommandScheduler.getInstance()::terminateOpMode);
    }

}

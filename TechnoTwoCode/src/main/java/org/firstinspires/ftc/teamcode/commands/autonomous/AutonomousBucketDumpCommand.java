package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadLevel1Command;
import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

public class AutonomousBucketDumpCommand extends SequentialCommandGroup {
    public AutonomousBucketDumpCommand(BucketSubsystem bucket) { // TODO: Add vision support
        super(new BucketUnloadLevel1Command(bucket));
    }
}
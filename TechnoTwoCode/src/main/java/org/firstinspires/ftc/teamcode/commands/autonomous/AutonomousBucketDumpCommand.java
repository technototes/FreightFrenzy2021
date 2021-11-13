package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

public class AutonomousBucketDumpCommand extends SequentialCommandGroup {
    public AutonomousBucketDumpCommand(BucketSubsystem bucket) { // TODO: Add vision support
        super(new BucketUnloadTopLevelCommand(bucket));
    }
}
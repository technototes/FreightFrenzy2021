package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.bucket.BucketCarryCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand2;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class AutonomousBucketDumpCommand extends SequentialCommandGroup {
    public AutonomousBucketDumpCommand(DumpSubsystem bucket) {
        super(new DumpCarryCommand2(bucket),
                  new DumpUnloadTopLevelCommand(bucket),
                  new WaitCommand(1));
    }
}
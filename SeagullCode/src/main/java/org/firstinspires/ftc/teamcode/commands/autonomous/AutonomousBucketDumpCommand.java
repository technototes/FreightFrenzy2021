package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCarryCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

//TODO: is already done but not sure should I touch this or not
public class AutonomousBucketDumpCommand extends SequentialCommandGroup {
    public AutonomousBucketDumpCommand(DumpSubsystem bucket) {
        super(true,
                new DumpCarryCommand(bucket),
                new DumpUnloadTopLevelCommand(bucket),
                new WaitCommand(0.7));
    }
}
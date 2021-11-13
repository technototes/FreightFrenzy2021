package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpUnloadTopLevelCommand extends SequentialCommandGroup {
    public DumpUnloadTopLevelCommand(DumpSubsystem dump) {
        super(new ArmTopLevelCommand(dump),
                new BucketUnloadTopLevelCommand(dump),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

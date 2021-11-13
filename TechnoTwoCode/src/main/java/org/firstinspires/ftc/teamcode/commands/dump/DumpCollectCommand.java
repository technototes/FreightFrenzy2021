package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCollectCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCollectCommand extends SequentialCommandGroup {
    public DumpCollectCommand(DumpSubsystem dump) {
        super(new ArmCollectCommand(dump),
                new BucketCollectCommand(dump),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

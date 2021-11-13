package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCarryCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketCarryCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCarryCommand extends SequentialCommandGroup {
    public DumpCarryCommand(DumpSubsystem dump) {
        super(new ArmCarryCommand(dump),
                new BucketCarryCommand(dump),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

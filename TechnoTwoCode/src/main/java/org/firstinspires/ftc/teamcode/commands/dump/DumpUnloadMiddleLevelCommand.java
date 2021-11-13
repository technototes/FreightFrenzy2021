package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpUnloadMiddleLevelCommand extends SequentialCommandGroup {
    public DumpUnloadMiddleLevelCommand(DumpSubsystem dump) {
        super(new ArmMiddleLevelCommand(dump),
                new BucketUnloadMiddleLevelCommand(dump),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}

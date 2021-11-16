package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Log;

import org.firstinspires.ftc.teamcode.commands.arm.ArmMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpUnloadMiddleLevelCommand extends SequentialCommandGroup {
    @Log.Number (name = "DumpUnloadMiddle")
    public float commandState = 400;

    public DumpUnloadMiddleLevelCommand(DumpSubsystem dump) {
        super(new ArmMiddleLevelCommand(dump),
                new BucketUnloadMiddleLevelCommand(dump));
    }

    @Override
    public void init() {
        commandState = 410;
        super.init();
    }

    @Override
    public void end(boolean cancel) {
        commandState = 420;
        super.end(cancel);
    }
}

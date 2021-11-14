package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Log;

import org.firstinspires.ftc.teamcode.commands.arm.ArmTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpUnloadTopLevelCommand extends SequentialCommandGroup {
    @Log.Number (name = "DumpUnloadTopLevel")
    public long commandState = 300;

    public DumpUnloadTopLevelCommand(DumpSubsystem dump) {
        super(new ArmTopLevelCommand(dump),
                new BucketUnloadTopLevelCommand(dump));
    }

    @Override
    public void init() {
        commandState = 310;
        super.init();
    }

    @Override
    public void end(boolean cancel) {
        commandState = 320;
        super.end(cancel);
    }
}

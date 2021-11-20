package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Log;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCollectCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCollectCommand extends SequentialCommandGroup {
    @Log.Number (name = "DumpCollect")
    public double commandState = 100;

    public DumpCollectCommand(DumpSubsystem dump) {
        super(new ArmCollectCommand(dump),
                new BucketCollectCommand(dump));
    }

    @Override
    public void initialize() {
        commandState = 110;
        super.initialize();
    }

    @Override
    public void end(boolean cancel) {
        commandState = 120;
        super.end(cancel);
    }

}

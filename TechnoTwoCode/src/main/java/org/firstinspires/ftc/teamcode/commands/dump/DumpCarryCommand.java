package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Log;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCarryCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketCarryCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCarryCommand extends SequentialCommandGroup {
    @Log.Number (name = "DumpCarry")
    public int commandState = 200;
    public DumpCarryCommand(DumpSubsystem dump) {
        super(new BucketCarryCommand(dump), new ArmCarryCommand(dump));
    }

    @Override
    public void initialize() {
        commandState = 210;
        super.initialize();
    }

    @Override
    public void end(boolean cancel) {
        commandState = 220;
        super.end(cancel);
    }
}

package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Log;

import org.firstinspires.ftc.samplecode.finch.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.commands.arm.ArmBottomLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadBottomLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import java.util.function.DoubleSupplier;


public class DumpUnloadBottomLevelCommand extends SequentialCommandGroup {
    @Log.Number (name = "DumpUnloadBottom")
    public DoubleSupplier commandState = ()->500.0;

    public DumpUnloadBottomLevelCommand(DumpSubsystem dump){
            super(new ArmBottomLevelCommand(dump),
                    new BucketUnloadBottomLevelCommand(dump));
    }

    @Override
    public void init() {
        commandState = ()-> 510.0;
        super.init();
    }

    @Override
    public void end(boolean cancel) {
        commandState = ()->520.0;
        super.end(cancel);
    }
}

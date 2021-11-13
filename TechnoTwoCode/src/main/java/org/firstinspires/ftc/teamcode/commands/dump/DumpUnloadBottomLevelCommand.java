package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.samplecode.finch.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.commands.arm.ArmBottomLevelCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.BucketUnloadBottomLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;


public class DumpUnloadBottomLevelCommand extends SequentialCommandGroup {
        public DumpUnloadBottomLevelCommand(DumpSubsystem dump){
            super(new ArmBottomLevelCommand(dump),
                    new BucketUnloadBottomLevelCommand(dump),
                    CommandScheduler.getInstance()::terminateOpMode);
    }

}

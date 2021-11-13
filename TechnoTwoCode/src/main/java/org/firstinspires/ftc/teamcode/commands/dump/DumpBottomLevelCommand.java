package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCollectedCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class DumpBottomLevelCommand extends SequentialCommandGroup {
    public DumpBottomLevelCommand(ArmSubsystem arm, DumpSubsystem dump){
        super (new ArmCollectedCommand(arm), new q)
    }

}

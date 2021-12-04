package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.commands.dump.*;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class HeightSelectCommand extends ChoiceCommand {
    public HeightSelectCommand(VisionSubsystem vision, DumpSubsystem dump){
        super(new Pair<>(vision.barcodePipeline::zero, new DumpUnloadBottomLevelCommand2(dump)),
                new Pair<>(vision.barcodePipeline::one, new DumpUnloadMiddleLevelCommand2(dump)),
                new Pair<>(vision.barcodePipeline::two, new DumpUnloadTopLevelCommand2(dump)));
    }
}

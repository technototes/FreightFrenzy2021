
package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class HeightSelectRedDuckCommand extends ChoiceCommand {
    public HeightSelectRedDuckCommand(DrivebaseSubsystem drive, DumpSubsystem dump, VisionSubsystem vision) {
        super(new Pair<>(vision.barcodePipeline::top, new UnloadTopRedDuckCommandGroup(drive, dump)),
                  new Pair<>(vision.barcodePipeline::middle, new UnloadMiddleRedDuckCommandGroup(drive, dump)),
                  new Pair<>(vision.barcodePipeline::bottom, new UnloadBottomRedDuckCommandGroup(drive, dump)));
    }
}

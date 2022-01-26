
package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class HeightSelectBlueDuckCommand extends ChoiceCommand {
    public HeightSelectBlueDuckCommand(DrivebaseSubsystem drive, DumpSubsystem dump, VisionSubsystem vision) {
        super(new Pair<>(vision.barcodePipeline::top, new UnloadTopBlueDuckCommandGroup(drive, dump)),
                  new Pair<>(vision.barcodePipeline::middle, new UnloadMiddleBlueDuckCommandGroup(drive, dump)),
                  new Pair<>(vision.barcodePipeline::bottom, new UnloadBottomBlueDuckCommandGroup(drive, dump)));
    }
}

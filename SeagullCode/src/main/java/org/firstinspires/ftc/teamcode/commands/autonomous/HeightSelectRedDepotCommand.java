
package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class HeightSelectRedDepotCommand extends ChoiceCommand {
    public HeightSelectRedDepotCommand(DrivebaseSubsystem drive, DumpSubsystem dump, IntakeSubsystem intake, VisionSubsystem vision) {
        super(new Pair<>(vision.barcodePipeline::top, new UnloadTopRedDepotCommandGroup(drive, dump, intake, vision)),
                  new Pair<>(vision.barcodePipeline::middle, new UnloadMiddleRedDepotCommandGroup(drive, dump, intake, vision)),
                  new Pair<>(vision.barcodePipeline::bottom, new UnloadBottomRedDepotCommandGroup(drive, dump, intake, vision)));
    }
}

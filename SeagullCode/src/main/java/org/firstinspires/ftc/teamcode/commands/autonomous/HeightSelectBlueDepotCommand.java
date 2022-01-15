
package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.commands.dump.*;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class HeightSelectBlueDepotCommand extends ChoiceCommand {
    public HeightSelectBlueDepotCommand(DrivebaseSubsystem drive, DumpSubsystem dump, IntakeSubsystem intake, VisionSubsystem vision) {
        super(new Pair<>(vision.barcodePipeline::zero, new UnloadTopBlueDepotCommandGroup(drive, dump, intake, vision)),
                  new Pair<>(vision.barcodePipeline::one, new UnloadMiddleBlueDepotCommandGroup(drive, dump, intake, vision)),
                  new Pair<>(vision.barcodePipeline::two, new UnloadBottomBlueDepotCommandGroup(drive, dump, intake, vision)));
    }
}

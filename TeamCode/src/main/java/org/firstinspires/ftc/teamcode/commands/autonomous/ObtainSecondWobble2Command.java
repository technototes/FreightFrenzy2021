package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseThenRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class ObtainSecondWobble2Command extends SequentialCommandGroup {
    public ObtainSecondWobble2Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                        new TrajectoryCommand(d, s.correctedTan(130), new Pair<>(s.correctedTan(-170), s.correctedSecondWobbleGrabPos())),
                        new WobbleLowerCommand(w)),
                new WobbleCloseThenRaiseCommand(w));
    }
}

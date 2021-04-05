package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerThenOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class DeliverFirstWobble2Command extends SequentialCommandGroup {
    public DeliverFirstWobble2Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                        new TrajectoryCommand(d,  new Pair<>(0.0, s.correctedPos(30, -5, 0)),
                                new Pair<>(s.correctedTan(0), s.correctedFirstWobbleDropPos())),
                        new WobbleRaiseCommand(w)),
                new WobbleLowerThenOpenCommand(w));
    }
}

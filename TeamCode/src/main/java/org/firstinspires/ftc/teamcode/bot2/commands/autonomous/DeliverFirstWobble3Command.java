package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleRotateCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

public class DeliverFirstWobble3Command extends SequentialCommandGroup {
    public DeliverFirstWobble3Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s) {
        super(new ParallelCommandGroup(
                        new TrajectoryCommand(d, new Pair<>(0.0, s.correctedPos(30, -7, 0)),
                                new Pair<>(s.correctedTan(0), s.correctedFirstWobbleDropPos())),
                        new WobbleRotateCommand(w, 0).sleep(2).andThen(new WobbleLowerCommand(w))
                ),
                new WobbleOpenCommand(w));
    }
}
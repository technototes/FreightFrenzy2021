package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class DeliverFirstWobble3Command extends SequentialCommandGroup {
    public DeliverFirstWobble3Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s) {
        super(new ParallelCommandGroup(
                        new TrajectoryCommand(d, new Pair<>(0.0, s.correctedPos(30, -10, 0)),
                                new Pair<>(s.correctedTan(0), s.correctedFirstWobbleDropPos())),
                        new InstantCommand(()->w.setTurretPosition(0)).sleep(2).then(new WobbleLowerCommand(w))
                ),
                new WobbleOpenCommand(w));
    }
}

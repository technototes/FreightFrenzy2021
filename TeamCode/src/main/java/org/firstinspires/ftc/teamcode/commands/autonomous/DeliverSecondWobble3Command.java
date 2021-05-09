package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerThenOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRotateRightCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class DeliverSecondWobble3Command extends SequentialCommandGroup {
    public DeliverSecondWobble3Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                new WobbleRaiseCommand(w).then(new InstantCommand(()->w.setTurretPosition(0))).then(new WobbleLowerCommand(w)),
                new TrajectoryCommand(d, 0, new Pair<>(-45.0, s.correctedSecondWobbleDropPos()))),
                new WobbleOpenCommand(w));
    }
}

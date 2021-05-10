package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.turret.TurretRotateLeftCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseThenRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRotateLeftCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class ObtainSecondWobble3Command extends SequentialCommandGroup {
    public ObtainSecondWobble3Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(
                new InstantCommand(()->w.setTurretPosition(1)),
                new WobbleLowerCommand(w).with(new StrafeCommand(d, s.correctedSecondWobbleGrabPos())),

                new WobbleCloseCommand(w));
    }
}

package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

public class ObtainSecondWobbleCommand extends SequentialCommandGroup {
    public ObtainSecondWobbleCommand(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                        new SplineCommand(d, s.correctedSecondWobbleGrabPos(), s.correctedTan(AutoState.SECOND_WOBBLE_GRAB_TAN)),
                        new WobbleLowerCommand(w)),
                new WobbleCloseCommand(w), new WobbleRaiseCommand(w));
    }
}

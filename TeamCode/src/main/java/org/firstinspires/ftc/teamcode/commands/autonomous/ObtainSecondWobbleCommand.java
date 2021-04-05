package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class ObtainSecondWobbleCommand extends SequentialCommandGroup {
    public ObtainSecondWobbleCommand(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                        new SplineCommand(d, s.correctedSecondWobbleGrabPos(), s.correctedTan(AutoState.SECOND_WOBBLE_GRAB_TAN)),
                        new WobbleLowerCommand(w)),
                new WobbleCloseCommand(w), new WobbleRaiseCommand(w));
    }
}

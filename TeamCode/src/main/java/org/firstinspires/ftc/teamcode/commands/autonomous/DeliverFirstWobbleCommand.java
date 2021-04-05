package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class DeliverFirstWobbleCommand extends SequentialCommandGroup {
    public DeliverFirstWobbleCommand(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                        new SplineCommand(d, s.correctedFirstWobbleDropPos(), s.correctedTan(0)),
                        new WobbleRaiseCommand(w)),
                new WobbleLowerCommand(w), new WobbleOpenCommand(w));
    }
}

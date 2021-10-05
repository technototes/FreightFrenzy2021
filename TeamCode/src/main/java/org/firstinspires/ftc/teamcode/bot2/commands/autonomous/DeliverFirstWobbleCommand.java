package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

public class DeliverFirstWobbleCommand extends SequentialCommandGroup {
    public DeliverFirstWobbleCommand(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
                        new SplineCommand(d, s.correctedFirstWobbleDropPos(), s.correctedTan(0)),
                        new WobbleRaiseCommand(w)),
                new WobbleLowerCommand(w), new WobbleOpenCommand(w));
    }
}

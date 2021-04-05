package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class DeliverSecondWobbleCommand extends SequentialCommandGroup {
    public DeliverSecondWobbleCommand(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(
                new ParallelCommandGroup(new WobbleRaiseCommand(w),
                new SplineCommand(d, s.correctedSecondWobbleDropPos(), s.correctedTan(0))),
                new WobbleLowerCommand(w), new WobbleOpenCommand(w));
    }
}

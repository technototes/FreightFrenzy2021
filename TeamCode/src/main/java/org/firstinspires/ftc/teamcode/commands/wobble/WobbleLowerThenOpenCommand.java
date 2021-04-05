package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class WobbleLowerThenOpenCommand extends SequentialCommandGroup {
    public WobbleLowerThenOpenCommand(WobbleSubsystem subsystem){
        super(new WobbleLowerCommand(subsystem), new WobbleOpenCommand(subsystem));
    }
}

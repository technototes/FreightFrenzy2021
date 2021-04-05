package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class WobbleLowerAndOpenCommand extends ParallelCommandGroup {
    public WobbleLowerAndOpenCommand(WobbleSubsystem subsystem){
        super(new WobbleLowerCommand(subsystem), new WobbleOpenCommand(subsystem));
    }
}

package org.firstinspires.ftc.teamcode.bot2.commands.wobble;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

public class WobbleLowerThenOpenCommand extends SequentialCommandGroup {
    public WobbleLowerThenOpenCommand(WobbleSubsystem subsystem){
        super(new WobbleLowerCommand(subsystem), new WobbleOpenCommand(subsystem));
    }
}

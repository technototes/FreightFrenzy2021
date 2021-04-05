package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class WobbleCloseThenRaiseCommand extends SequentialCommandGroup {
    public WobbleCloseThenRaiseCommand(WobbleSubsystem subsystem){
        super(new WobbleCloseCommand(subsystem), new WobbleRaiseCommand(subsystem));
    }
}

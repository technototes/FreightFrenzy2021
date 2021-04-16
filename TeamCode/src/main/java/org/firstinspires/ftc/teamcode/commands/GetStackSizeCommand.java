package org.firstinspires.ftc.teamcode.commands;

import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class GetStackSizeCommand extends WaitCommand {
    public VisionSubsystem subsystem;
    public AutoState autoState;
    public GetStackSizeCommand(VisionSubsystem s, AutoState a){
        super(0.1);
        subsystem = s;
        autoState = a;
    }

    @Override
    public void init() {
        autoState.setStackSize(subsystem.getStackSizeEnum());
    }
}

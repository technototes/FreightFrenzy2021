package org.firstinspires.ftc.teamcode.commands.wobble;

import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

public class WobbleLowerCommand extends WaitCommand {
    public WobbleSubsystem subsystem;

    public WobbleLowerCommand(WobbleSubsystem w){
        //COOLDOWN
        super(0.5);
        //add   Requirements(w);
        subsystem = w;
    }

    @Override
    public void execute() {
        subsystem.armServo.setPosition(commandRuntime.seconds()*2);
    }

}

package org.firstinspires.ftc.teamcode.bot2.commands.wobble;

import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

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
        subsystem.armServo.setPosition(getRuntime().seconds()*2);
    }

}

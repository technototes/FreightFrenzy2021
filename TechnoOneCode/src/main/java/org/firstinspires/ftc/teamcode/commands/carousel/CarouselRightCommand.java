package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselRightCommand implements Command {

    public CarouselSubsystem subsystem;

    public CarouselRightCommand(CarouselSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute(){
        subsystem.right();
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.stop();
    }
}

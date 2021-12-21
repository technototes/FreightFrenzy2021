package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselBlueFastCommand implements Command {
    private CarouselSubsystem carouselSubsystem;

    public CarouselBlueFastCommand (CarouselSubsystem s) {
        carouselSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        carouselSubsystem.blue_fast();

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        carouselSubsystem.stop();
    }
}









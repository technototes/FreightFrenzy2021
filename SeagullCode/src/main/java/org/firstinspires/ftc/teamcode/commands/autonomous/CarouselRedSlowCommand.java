package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselRedSlowCommand implements Command{
    private CarouselSubsystem carouselSubsystem;

    public CarouselRedSlowCommand(CarouselSubsystem s) {
        carouselSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        carouselSubsystem.red_auto_slow();
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

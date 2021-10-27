package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselStopCommand implements Command {
    /**
     * Implements Command Function to create stop command
     */
    CarouselSubsystem subsystem;

    public CarouselStopCommand(CarouselSubsystem s) {
        /**
         * calls carousel subsystem code for stop
         */
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.stop();
    }
    /**
     * calls Carousel Stop from subsystem code and sets speed to 0 to stop motor
     */
}

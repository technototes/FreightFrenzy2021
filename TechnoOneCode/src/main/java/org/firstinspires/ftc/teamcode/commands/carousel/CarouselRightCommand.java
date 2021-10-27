package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselRightCommand implements Command {
    /**
     * Implements Command Function to create right command
     */
    CarouselSubsystem subsystem;

    public CarouselRightCommand(CarouselSubsystem s){
        /**
         * calls carousel subsystem code for turning right
         */
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute(){
        subsystem.right();
    }
    /**
     * calls Carousel right and sets speed to 1 for motor to turn right from subsystem code
     */
}

package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselLeftCommand implements Command {
    /**
     * Implements Command Function to create left command
     */
    CarouselSubsystem subsystem;

    public CarouselLeftCommand(CarouselSubsystem s){
        /**
         * calls carousel subsystem code for turning left
         */
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute(){
        subsystem.left();
    }
    /**
     * calls Carousel left and sets speed to -1 for motor to turn left from subsystem code
     */
}


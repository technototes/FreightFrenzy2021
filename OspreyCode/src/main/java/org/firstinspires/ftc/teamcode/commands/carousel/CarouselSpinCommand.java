package org.firstinspires.ftc.teamcode.commands.carousel;

import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.SPIN_OFFSET;

import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselSpinCommand implements Command {

    CarouselSubsystem subsystem;

    public CarouselSpinCommand(CarouselSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        if (RobotConstants.getAlliance() == Alliance.RED) subsystem.left();
        else subsystem.right();
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.stop();
    }
}


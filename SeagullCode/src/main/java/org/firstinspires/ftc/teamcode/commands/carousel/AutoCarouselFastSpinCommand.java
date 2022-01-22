package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class AutoCarouselFastSpinCommand implements Command {

    CarouselSubsystem subsystem;

    public AutoCarouselFastSpinCommand(CarouselSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute(){
        if(AutonomousConstants.ALLIANCE == Alliance.RED) subsystem.red_fast();
        else subsystem.blue_fast();
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


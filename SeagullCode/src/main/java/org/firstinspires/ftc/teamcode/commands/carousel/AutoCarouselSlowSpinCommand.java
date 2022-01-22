package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class AutoCarouselSlowSpinCommand implements Command {

    CarouselSubsystem subsystem;

    public AutoCarouselSlowSpinCommand(CarouselSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute(){
        if(AutonomousConstants.ALLIANCE == Alliance.RED) subsystem.red_auto_slow();
        else subsystem.blue_auto_slow();
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


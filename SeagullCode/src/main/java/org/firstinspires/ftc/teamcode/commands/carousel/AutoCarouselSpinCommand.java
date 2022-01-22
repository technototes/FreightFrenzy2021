package org.firstinspires.ftc.teamcode.commands.carousel;

import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class AutoCarouselSpinCommand extends SequentialCommandGroup {
    public AutoCarouselSpinCommand(CarouselSubsystem s) {
        super(
                new AutoCarouselSlowSpinCommand(s).withTimeout(1),
                new AutoCarouselFastSpinCommand(s).withTimeout(0.9)
        );
    }
}


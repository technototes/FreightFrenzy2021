package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

@SuppressWarnings("unused")

public class CarouselSubsystem implements Subsystem, Supplier<Double> {
    public Motor<DcMotorEx> motor;
    public CarouselSubsystem(Motor<DcMotorEx> m){
        motor = m;
    }

    @Override
    public Double get() {
        return motor.getSpeed();
    }
}

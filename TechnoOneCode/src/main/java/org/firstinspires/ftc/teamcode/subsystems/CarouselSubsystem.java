package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

@SuppressWarnings("unused")

public class CarouselSubsystem implements Subsystem, Supplier<Double> {

    @Config
    public static class CarouselConstants{
        public static double CAROUSEL_RIGHT_SPEED = 1;
        public static double CAROUSEL_LEFT_SPEED = -1;
        public static double CAROUSEL_STOP_SPEED = 0;
    }

    public Motor<DcMotorEx> motor;

    public CarouselSubsystem(Motor<DcMotorEx> m){motor = m;}

    public void right(){motor.setSpeed(CAROUSEL_RIGHT_SPEED);}

    public void left(){motor.setSpeed(CAROUSEL_LEFT_SPEED);}

    public void stop(){motor.setSpeed(CAROUSEL_STOP_SPEED);}
    @Override
    public Double get() {
        return motor.getSpeed();
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.CAROUSEL_RIGHT_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.CAROUSEL_STOP_SPEED;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

@SuppressWarnings("unused")

public class CarouselSubsystem implements Subsystem, Supplier<Double> {

    /**
     * Carousel Constants for measuring speed of carousel motor
     */

    @Config
    public static class CarouselConstants{
        public static double CAROUSEL_RIGHT_SPEED = 1;
        public static double CAROUSEL_LEFT_SPEED = -1;
        public static double CAROUSEL_STOP_SPEED = 0;
    }

    public Motor<DcMotorEx> motor;

    public CarouselSubsystem(Motor<DcMotorEx> m){motor = m;}

    public void right(){motor.setSpeed(CAROUSEL_RIGHT_SPEED);}

    /**
     * when called by Carousel Right Command, sets motor to turn carousel right
     */

    public void left(){motor.setSpeed(CarouselConstants.CAROUSEL_LEFT_SPEED);}

    /**
     * when called by Carousel Left Command, sets motor to turn carousel left
     */

    public void stop(){motor.setSpeed(CAROUSEL_STOP_SPEED);}

    /**
     * when called by Carousel Stop Command, stops carousel motor
     */

    @Override
    public Double get() {
        return motor.getSpeed();
        /**
         * gets current motor speed
         */
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.CAROUSEL_STOP_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.MAX_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.MIN_SPEED;


@SuppressWarnings("unused")

public class CarouselSubsystem implements Subsystem, Supplier<Double> {

    /**
     * Carousel Constants for measuring speed of carousel motor
     */

    @Config
    public static class CarouselConstants{
        public static double MAX_SPEED = 1;
        public static double MIN_SPEED = 0.2;
        public static double CAROUSEL_STOP_SPEED = 0;
        public static double SPIN_OFFSET = 4;
    }

    public Motor<DcMotorEx> motor;

    public CarouselSubsystem(Motor<DcMotorEx> m){
        motor = m;
    }

    public void right(){motor.setSpeed(MAX_SPEED);}

    public void right(double speed){motor.setSpeed(MAX_SPEED*Math.max(MIN_SPEED, speed));}

    /**
     * when called by Carousel Right Command, sets motor to turn carousel right
     */

    public void left(){motor.setSpeed(-MAX_SPEED);}
    public void left(double speed){motor.setSpeed(-MAX_SPEED*Math.max(MIN_SPEED, speed));}

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

package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem.CarouselConstants.*;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;


public class CarouselSubsystem implements Subsystem, Supplier<Double> {

    public static class CarouselConstants{
        public static double CAROUSEL_STOP_SPEED = 0;
        public static double CAROUSEL_BLUE_SLOW = 0.85;
        public static double CAROUSEL_BLUE_FAST = 1;
        public static double CAROUSEL_RED_SLOW = -0.85;
        public static double CAROUSEL_RED_FAST = -1;
        public static double CAROUSEL_AUTO_RED_SLOW = -0.6;
        public static double CAROUSEL_AUTO_BLUE_SLOW = 0.6;
    }

    public Motor<DcMotorEx> motor;
    public CarouselSubsystem(Motor<DcMotorEx> m){
        motor = m;
        motor.getDevice().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void stop() {
        motor.setSpeed(CAROUSEL_STOP_SPEED);
    }
    public void red_slow(){
        motor.setSpeed(CAROUSEL_RED_SLOW);
    }
    public void red_fast(){
        motor.setSpeed(CAROUSEL_RED_FAST);
    }
    public void blue_slow(){
        motor.setSpeed(CAROUSEL_BLUE_SLOW);
    }
    public void blue_fast(){
        motor.setSpeed(CAROUSEL_BLUE_FAST);
    }

    public void red_auto(){
        motor.setSpeed(CAROUSEL_AUTO_RED_SLOW);
    }
    public void blue_auto(){
        motor.setSpeed(CAROUSEL_AUTO_BLUE_SLOW);
    }


    @Override
    public Double get() {
        return motor.getSpeed();
    }
}

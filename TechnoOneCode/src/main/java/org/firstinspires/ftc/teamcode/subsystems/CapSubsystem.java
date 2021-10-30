package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class CapSubsystem implements Subsystem, Supplier<Double> {
    public static class CapConstant{
        public static final double DEADZONE = 0.1;
    }

    public Servo capServo;

    public CapSubsystem(Servo s){
        capServo = s;
    }

    public void goto_position(double pos){
        capServo.setPosition(pos);
    }

    @Override
    public Double get() {
        return null;
    }
}

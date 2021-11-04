package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.CapSubsystem.CapConstant.RESTING;

public class CapSubsystem implements Subsystem, Supplier<Double> {
    public static class CapConstant{
        public static double RESTING = 0.1;
    }

    public Servo capServo;

    public CapSubsystem(Servo s){
        capServo = s;
    }

    public void setCap(double pos){
        capServo.setPosition(pos);
    }

    public void restCap(){
        setCap(RESTING);
    }

    @Override
    public Double get() {
        return null;
    }
}

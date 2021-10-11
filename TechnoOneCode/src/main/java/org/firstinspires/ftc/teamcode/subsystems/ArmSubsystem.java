package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class ArmSubsystem implements Subsystem, Supplier<Double> {
    public Servo armServo;
    public ArmSubsystem(Servo a){
        armServo = a;
    }
    public void fullyIn(){
        armServo.setPosition(0);
    }
    public void fullyOut(){
        armServo.setPosition(1);
    }
    public void setPosition(double v){
        armServo.setPosition(v);
    }

    @Override
    public Double get() {
        return armServo.getPosition();
    }
}

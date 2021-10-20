package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Differential;

public class DepositSubsystem implements Subsystem {
    public Servo servoLeft;
    public Servo servoRight;
    public Differential differential;
    public DepositSubsystem(Servo l, Servo r){
        servoLeft = l;
        servoRight = r;
        differential = new Differential(l::setPosition, r::setPosition, Differential.DifferentialPriority.DIFFERENCE)
        .setLimits(0, 0.5);
    }
    public void dump(){
        differential.setDifferenceOutput(-0.1);
    }
    public void carry(){

        differential.setDifferenceOutput(0);
    }
    public void collect(){
        differential.setDifferenceOutput(0.1);
    }
    public void fullyIn(){

        differential.setAverageOutput(0.4);
    }
    public void fullyOut(){

        differential.setAverageOutput(0.1);
    }
    public void setExtension(double v){

        differential.setAverageOutput(v);
    }
}

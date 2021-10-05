package org.firstinspires.ftc.teamcode.finch.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ArmSubsystem implements Subsystem {
    public Servo servo;
    public ArmSubsystem(Servo s){
        servo = s;
    }
    public void raise(){
        servo.setPosition(1);
    }
    public void lower(){
        servo.setPosition(0);
    }
    public void carry(){
        servo.setPosition(0.5);
    }
}

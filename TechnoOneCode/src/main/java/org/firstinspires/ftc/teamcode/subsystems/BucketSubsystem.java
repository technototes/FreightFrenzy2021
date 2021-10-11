package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class BucketSubsystem implements Subsystem, Supplier<String> {
    public Servo bucketServo;
    public BucketSubsystem(Servo b){
        bucketServo = b;
    }

    public void dump(){
        bucketServo.setPosition(1);
    }
    public void carry(){
        bucketServo.setPosition(0.5);
    }
    public void raise(){
        bucketServo.setPosition(0);
    }

    @Override
    public String get() {
        return bucketServo.getPosition() == 1 ? "dumped" : bucketServo.getPosition() == 0 ? "raised" : "carrying";
    }
}

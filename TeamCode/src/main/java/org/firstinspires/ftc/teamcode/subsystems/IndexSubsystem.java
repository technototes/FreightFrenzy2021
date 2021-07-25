package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;

import java.util.function.Supplier;

public class IndexSubsystem extends ServoSubsystem implements Supplier<String> {



    public enum ArmPositon{
        EXTENDED(0.12), RETRACTED(0.28);
        double pos;
        ArmPositon(double v){
            pos = v;
        }
        public double getPositon(){
            return pos;
        }
    }
 


    public Servo  arm;
    public ArmPositon armPositon = ArmPositon.RETRACTED;

    public IndexSubsystem(Servo a){
        super(a);
        arm = a;
    }
    //subsystem functions
        public void extendArm(){
        arm.setPosition(ArmPositon.EXTENDED.getPositon());
        armPositon = ArmPositon.EXTENDED;
    }
    public void retractArm(){
        arm.setPosition(ArmPositon.RETRACTED.getPositon());
        armPositon = ArmPositon.RETRACTED;
    }


    @Override
    public String get() {
        return "ARM: "+armPositon;
    }
}

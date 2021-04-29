package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;
import com.technototes.logger.Stated;

public class IndexSubsystem extends ServoSubsystem implements Stated<String>{



    public enum ArmPositon{
        EXTENDED(0.3), RETRACTED(0.12);
        double pos;
        ArmPositon(double v){
            pos = v;
        }
        public double getPositon(){
            return pos;
        }
    }
    public enum PivotPositon{
        RAISED(0.65), LOWERED(0.1);
        double pos;
        PivotPositon(double v){
            pos = v;
        }
        public double getPositon(){
            return pos;
        }
    }



    public Servo pivot, arm;
    public PivotPositon pivotPositon = PivotPositon.LOWERED;
    public ArmPositon armPositon = ArmPositon.RETRACTED;

    public IndexSubsystem(Servo p, Servo a){
        super(p, a);
        pivot = p;
        arm = a;
    }
    //subsystem functions
    public void raiseToShooter(){
        pivot.setPosition(PivotPositon.RAISED.getPositon());
        pivotPositon = PivotPositon.RAISED;
    }
    public void lowerToIntake(){
        pivot.setPosition(PivotPositon.LOWERED.getPositon());
        pivotPositon = PivotPositon.LOWERED;
    }
    public void extendArm(){
        arm.setPosition(ArmPositon.EXTENDED.getPositon());
        armPositon = ArmPositon.EXTENDED;
    }
    public void retractArm(){
        arm.setPosition(ArmPositon.RETRACTED.getPositon());
        armPositon = ArmPositon.RETRACTED;
    }


    @Override
    public String getState() {
        return "PIVOT: "+pivotPositon+". ARM: "+armPositon;
    }
}

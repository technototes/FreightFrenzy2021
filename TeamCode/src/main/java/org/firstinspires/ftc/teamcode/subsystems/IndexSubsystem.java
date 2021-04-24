package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;
import com.technototes.logger.Stated;

public class IndexSubsystem extends ServoSubsystem implements Stated<String>{

    public Servo pivot, arm;


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

//    public enum IndexState{
//        EMPTY(0), ONE_RING(1), TWO_RINGS(2), FULL(3);
//        public int numRings;
//        IndexState(int rings){
//            numRings = rings;
//        }
//        public int getNumRings(){
//            return numRings;
//        }
//
//    }

    //public IndexState indexState;
    public PivotPositon pivotPositon = PivotPositon.LOWERED;
    public ArmPositon armPositon = ArmPositon.RETRACTED;

    public IndexSubsystem(Servo p, Servo a){
        super(p, a);
        pivot = p;
        arm = a;
        //indexState = IndexState.EMPTY;
    }

    public void raiseToShooter(){
        pivot.setPosition(PivotPositon.RAISED.getPositon());
        pivotPositon = PivotPositon.RAISED;
        //0.62
    }
    public void lowerToIntake(){
        pivot.setPosition(PivotPositon.LOWERED.getPositon());
        pivotPositon = PivotPositon.LOWERED;
    }

    public void extendArm(){
        arm.setPosition(ArmPositon.EXTENDED.getPositon());
        armPositon = ArmPositon.EXTENDED;
    }//0.45

    public void retractArm(){
        arm.setPosition(ArmPositon.RETRACTED.getPositon());
        armPositon = ArmPositon.RETRACTED;
    }
//    //public int getNumRings(){
//        return indexState.getNumRings();
//    }


//    @Override
//    public Integer getState() {
//        return getNumRings();
//    }
//
//    public boolean isFull() {
//        return getNumRings()==3;
//    }


    @Override
    public String getState() {
        return "PIVOT: "+pivotPositon+". ARM: "+armPositon;
    }
}

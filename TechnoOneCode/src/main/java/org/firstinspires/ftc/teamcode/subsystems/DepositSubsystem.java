package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.*;
@SuppressWarnings("unused")

public class DepositSubsystem implements Subsystem, Supplier<String> {
    /**
     * Deposit is am arm to hold and drop freight
     */
    @Config
    public static class DepositConstants{
        //public static double MIN = 0, MAX = 0.5;
        public static double DUMP = 1, CARRY = 0.5, COLLECT = 0;
        public static double IN = 1, OUT = 0;
    }

    public Servo dumpServo;
    public Servo armServo;
    //public Differential differential;
    public DepositSubsystem(Servo l, Servo r){
        dumpServo = l;
        armServo = r;
//        differential = new Differential(l::setPosition, r::setPosition, Differential.DifferentialPriority.DIFFERENCE)
//        .setLimits(MIN, MAX);
    }

    /**
     * Sets the servo controlling the cup to a constant value which dumps the freight
     * to a target
     */
    public void dump(){
        dumpServo.setPosition(DUMP);
        //differential.setDeviationOutput(DUMP);
    }

    /**
     * Sets a servo controlling the cup to a constant value which carries the freight while
     * the robot is moving
     */
    public void carry(){
        dumpServo.setPosition(CARRY);
//        differential.setDeviationOutput(CARRY);
    }
    /**
     * Sets a servo to a constant value which puts the cup in position to take the freight once
     * it's intaked
     */
    public void collect(){
        dumpServo.setPosition(COLLECT);
        //differential.setDeviationOutput(COLLECT);
    }

    /**
     * Sets a servo to a custom value based off the specific position the driver wants the cup
     * to be when dumping the freight
     */
    public void setDump(double v){
        dumpServo.setPosition(Range.clip(v, CARRY, DUMP));
    }

    
    public void fullyIn(){
        armServo.setPosition(IN);
        //differential.setAverageOutput(IN);
    }
    public void fullyOut(){
        armServo.setPosition(OUT);
        //differential.setAverageOutput(OUT);
    }
    public void setExtension(double v){
        armServo.setPosition(Range.clip(v, OUT, IN));
        //differential.setAverageOutput(Range.clip(v, OUT, IN));
    }
    public void translateExtension(double v){
        armServo.incrementPosition(v);
    }


    @Override
    public String get() {
        return "EXTENSION: "+armServo.getPosition()+", DUMP: "+dumpServo.getPosition();
        //return "EXTENSION: "+differential.getAverage()+", DUMP: "+differential.getDeviation();
    }

}

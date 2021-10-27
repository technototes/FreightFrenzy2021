package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.CARRY;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.COLLECT;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.DUMP;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.IN;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.OUT;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;
@SuppressWarnings("unused")

public class DepositSubsystem implements Subsystem, Supplier<String> {
    /**
     * Deposit is am arm to hold and drop freight
     * @Config allows you to mess with variables without messing with the code
     * Especially good when trying to set the position of different mechanisms
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
//        Don't need differentials
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
     * Sets a servo to a custom constant value based off the specific position the driver wants the cup
     * to be when dumping the freight
     */
    public void setDump(double v){
        dumpServo.setPosition(Range.clip(v, CARRY, DUMP));
    }

    /**
     * Sets the arm servo to a constant value to retract the arm of the robot
     */
    public void fullyIn(){
        armServo.setPosition(IN);
        //differential.setAverageOutput(IN);
    }
    /**
     * Sets the arm servo to a constant value to extend the arm of the robot
     */
    public void fullyOut(){
        armServo.setPosition(OUT);
        //differential.setAverageOutput(OUT);
    }
    /**
     * Sets the arm servo to a custom constant value to extend the arm of the robot
     */
    public void setExtension(double v){
        armServo.setPosition(Range.clip(v, OUT, IN));
        //differential.setAverageOutput(Range.clip(v, OUT, IN));
    }
    /**
     * Sets a custom value that will add to the Arm servo's value, setting it to a new position
     * for extension
     */
    public void translateExtension(double v){
        armServo.incrementPosition(v);
    }

    /**
     *Method to display the extension and dump value on the telemetry to the driver knows
     * how much they are extending the arm and positioning the cup
     */
    @Override
    public String get() {
        return "EXTENSION: "+armServo.getPosition()+", DUMP: "+dumpServo.getPosition();
        //return "EXTENSION: "+differential.getAverage()+", DUMP: "+differential.getDeviation();
    }

}

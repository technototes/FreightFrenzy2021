package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.hardware.servo.ServoProfiler;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.CARRY;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.COLLECT;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.CONSTRAINTS;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.DOWN;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.DUMP;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.DIFFERENTIAL;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.FAKE_CARRY;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.IN;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.OUT;
import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.UP;

@SuppressWarnings("unused")

public class ArmSubsystem implements Subsystem, Supplier<String> {
    /**
     * Deposit is am arm to hold and drop freight
     * @Config allows you to mess with variables without messing with the code
     * Especially good when trying to set the position of different mechanisms
     */
    @Config
    public static class ArmConstants {
        //public static double MIN = 0, MAX = 0.5;
        public static double DUMP = 0.55, CARRY = 0.25, FAKE_CARRY = 0.15, COLLECT = 0.05, AUTO_CARRY = 0.25;
        public static double IN = 0.02, UP = 0.3, OUT = 0.6, DOWN = 0.75;
        public static double DIFFERENTIAL = 2.8;
        public static ServoProfiler.Constraints CONSTRAINTS = new ServoProfiler.Constraints(3, 3, 5);
    }

    public Servo dumpServo;
    public Servo armServo;
    public ServoProfiler armController;
    public ArmSubsystem(Servo l, Servo r){
        dumpServo = l;
        armServo = r;
        armController = new ServoProfiler(armServo).setConstraints(CONSTRAINTS).setTargetPosition(UP);

    }

    /**
     * Sets the servo controlling the cup to a constant value which dumps the freight
     * to a target
     */
    public void dump(){
        setDump(DUMP);
    }

    /**
     * Sets a servo controlling the cup to a constant value which carries the freight while
     * the robot is moving
     */
    public void carry(){
        setDump(CARRY);
    }
    public void fakeCarry(){
        setDump(FAKE_CARRY);
    }

    /**
     * Sets a servo to a constant value which puts the cup in position to take the freight once
     * it's intaked
     */
    public void collect(){
        setDump(COLLECT);
    }

    /**
     * Sets a servo to a custom constant value based off the specific position the driver wants the cup
     * to be when dumping the freight
     */
    public void setDump(double v){
        targetDumpPosition = Range.clip(v, COLLECT, DUMP);
    }

    /**
     * Sets the arm servo to a constant value to retract the arm of the robot
     */
    public void up(){
        setArm(UP);
    }

    /**
     * Sets the arm servo to a constant value to retract the arm of the robot
     */
    public void in(){
        setArm(IN);
    }
    /**
     * Sets the arm servo to a constant value to extend the arm of the robot
     */
    public void out(){
        setArm(OUT);
    }

    public void down(){
        setArm(DOWN);
    }
    /**
     * Sets the arm servo to a custom constant value to extend the arm of the robot
     */
    public void setArm(double v){
        armController.setTargetPosition(Range.clip(v, IN, DOWN));
    }
    /**
     * Sets a custom value that will add to the Arm servo's value, setting it to a new position
     * for extension
     */
    public void translateArm(double v){
        setArm(armServo.getPosition()+v);
    }

    /**
     *Method to display the extension and dump value on the telemetry to the driver knows
     * how much they are extending the arm and positioning the cup
     */
    @Override
    public String get() {
        return "ARM: "+armServo.getPosition()+", DUMP: "+ targetDumpPosition;
        //return "EXTENSION: "+differential.getAverage()+", DUMP: "+differential.getDeviation();
    }

    private double targetDumpPosition = 0.2;
    @Override
    public void periodic() {
        armController.update();
        dumpServo.setPosition(targetDumpPosition +armServo.getPosition()/ DIFFERENTIAL);
    }
}

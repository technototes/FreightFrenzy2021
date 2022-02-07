package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.CapSubsystem.CapConstants.*;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.hardware.servo.ServoProfiler;
import com.technototes.library.subsystem.Subsystem;


import java.util.function.Supplier;


public class CapSubsystem implements Subsystem, Supplier<String> {
    @Config
    public static class CapConstants {
            public static double TURRET_INIT = 0.8, TURRET_PICKUP = 0.1, TURRET_CARRY = 0.8, TURRET_CAP = 0.5;
            public static double CLAW_OPEN = 0.1, CLAW_CLOSE = 0.53;
            public static double ARM_UP = 1, ARM_CAP = 0.85, ARM_INIT = 0.4, ARM_DOWN = 0.05;
            public static ServoProfiler.Constraints ARM_CONSTRAINTS = new ServoProfiler.Constraints(5, 5, 5);
            public static ServoProfiler.Constraints TURRET_CONSTRAINTS = new ServoProfiler.Constraints(3, 2, 2);

    }
    public Servo armServo;
    public Servo clawServo;

    public Servo turretServo;

    public ServoProfiler armProfiler;
    public ServoProfiler turretProfiler;


    public CapSubsystem(Servo arm, Servo claw, Servo turret){
        CommandScheduler.getInstance().register(this);
        armServo = arm;
        clawServo = claw;
        turretServo = turret;
        armProfiler = new ServoProfiler(armServo).setServoRange(0.4).setConstraints(ARM_CONSTRAINTS).setTargetPosition(ARM_INIT);
        turretProfiler = new ServoProfiler(turretServo).setConstraints(TURRET_CONSTRAINTS).setTargetPosition(TURRET_INIT);
    }

    public void open(){
        clawServo.setPosition(CLAW_OPEN);
    }

    public void close(){
        clawServo.setPosition(CLAW_CLOSE);
    }

    public void up(){
        armProfiler.setTargetPosition(ARM_UP);
        turretProfiler.setTargetPosition(TURRET_CARRY);
        clawServo.setPosition(CLAW_CLOSE);
    }
    public void raise(){
        armProfiler.setTargetPosition(ARM_CAP);
    }
    public void raise2(){
        turretProfiler.setTargetPosition(TURRET_CAP);
    }
    public void down(){
        armProfiler.setTargetPosition(ARM_DOWN);
        turretProfiler.setTargetPosition(TURRET_PICKUP);
    }
    public void translateArm(double translation){
        armProfiler.translateTargetPosition(translation);
    }
    public void translateTurret(double translation){
        turretProfiler.translateTargetPosition(translation);
    }

    @Override
    public void periodic() {
        turretProfiler.update();
        armProfiler.update();
    }

    @Override
    public String get() {
        return "CLAW: "+clawServo.getPosition()+", ARM: "+armProfiler.getTargetPosition()+", TURRET: "+turretProfiler.getTargetPosition();
    }

}

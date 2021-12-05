package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.teamcode.commands.cap.CapTimeCommand;

import java.util.function.Supplier;


public class CapSubsystem implements Subsystem, Supplier<Double> {
    @Config
    public static class CapConstants {
        public static double TOP = 0.7;
        public static double CARRY = 0.4;
        public static double COLLECT = 0.04;

    }

    public Servo capServo;

    public CapSubsystem(Servo s){
        capServo = s;
        setDefaultCommand(new CapTimeCommand(this));
    }

    public boolean setCap(double pos){
        return capServo.setPositionAsync(pos, 1);
    }

    @Override
    public Double get() {
        return capServo.getPosition();
    }
}

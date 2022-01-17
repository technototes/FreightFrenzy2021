package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;


@SuppressWarnings("unused")

public class ExtensionSubsystem implements Subsystem, Supplier<String> {
    @Config
    public static class ExtensionConstants {
        public static double SNAP_1 = 0, SNAP_2= Math.PI;
        public static double IN = 0.46, SHARED = 0.3, TELEOP_ALLIANCE = 0.35, STEAL_SHARED = 0.1, LOW_GOAL_AUTO = 0,  OUT = 0;
        public static double LEFT = 0, CENTER = 0.5, RIGHT = 1;
    }

    public Servo slideServo;
    public Servo turretServo;
    public ExtensionSubsystem(Servo s, Servo t){
        slideServo = s;
        turretServo = t;

    }

    public void left(){
        turretServo.setPosition(ExtensionConstants.LEFT);
    }

    public void right(){
        turretServo.setPosition(ExtensionConstants.RIGHT);
    }

    public void center(){
        turretServo.setPosition(ExtensionConstants.CENTER);
    }

    public void setTurret(double v){
        turretServo.setPosition(Range.clip(v, ExtensionConstants.LEFT, ExtensionConstants.RIGHT));
    }


    public boolean isSlideOut(){
        return slideServo.getPosition() < ExtensionConstants.IN-0.05;
    }
    public boolean isSlideIn(){
        return !isSlideOut();
    }


    public void fullyIn(){
        slideServo.setPosition(ExtensionConstants.IN);
    }

    public void fullyOut(){
        slideServo.setPosition(ExtensionConstants.OUT);
    }

    public void setSlide(double v){
        slideServo.setPosition(Range.clip(v, ExtensionConstants.OUT, ExtensionConstants.IN));
    }

    public void translateSlide(double v){
        setSlide(slideServo.getPosition()+v);
    }

    public void translateTurret(double v){
        setTurret(turretServo.getPosition()+v);
    }


    /**
     *Method to display the extension and dump value on the telemetry to the driver knows
     * how much they are extending the arm and positioning the cup
     */
    @Override
    public String get() {
        return "TURRET: "+ turretServo.getPosition()+", SLIDE: "+ slideServo.getPosition();
        //return "EXTENSION: "+differential.getAverage()+", DUMP: "+differential.getDeviation();
    }

}

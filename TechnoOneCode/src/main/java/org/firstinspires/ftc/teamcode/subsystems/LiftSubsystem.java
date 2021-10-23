package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftConstants.*;

@SuppressWarnings("unused")
public class LiftSubsystem implements Subsystem, Supplier<Double> {
    @Config
    public static class LiftConstants {
        public static double LIFT_UPPER_LIMIT = 2000.0;
        public static double LIFT_LOWER_LIMIT = 0.0;
        public static double DEADZONE = 0.1;
        public static final PIDCoefficients PID = new PIDCoefficients(0.002, 0, 0);

    }
    public EncodedMotor<DcMotorEx> liftMotor;

    public PIDFController pidController;
    public boolean isFollowing = false;

    public LiftSubsystem(EncodedMotor<DcMotorEx> l){
        liftMotor = l;
        l.zeroEncoder();
        pidController = new PIDFController(PID, 0, 0, 0, (x,y)->0.1);
    }

    public void setLiftPosition(double pos){
        pidController.setTargetPosition(Range.clip(pos, LIFT_LOWER_LIMIT, LIFT_UPPER_LIMIT));
    }

    public void liftToTop(){
        setLiftPosition(LIFT_UPPER_LIMIT);
    }

    public void liftToBottom(){
        setLiftPosition(LIFT_LOWER_LIMIT);
    }

    @Override
    public void periodic() {
        liftMotor.setSpeed(Math.max(-0.5,pidController.update(liftMotor.get())));
    }

    public boolean isAtTarget(){
        return Math.abs(pidController.getTargetPosition() - liftMotor.get()) < DEADZONE;
    }
    public void stop(){
        pidController.reset();
        isFollowing = false;
    }

    @Override
    public Double get() {
        return liftMotor.get();
    }

}

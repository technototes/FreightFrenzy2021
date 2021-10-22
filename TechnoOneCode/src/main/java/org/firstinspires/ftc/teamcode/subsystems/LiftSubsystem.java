package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftConstants.*;

@SuppressWarnings("unused")
public class LiftSubsystem implements Subsystem, Supplier<Double> {
    @Config
    public static class LiftConstants {
        public static double LIFT_UPPER_LIMIT = 1000.0;
        public static double LIFT_LOWER_LIMIT = 0.0;
        public static double DEADZONE = 0.1;

    }
    public EncodedMotor<DcMotorEx> liftMotor;

    public static final PIDCoefficients pidCoefficients = new PIDCoefficients(10, 0 , 0);
    public PIDFController pidController = new PIDFController(pidCoefficients);
    public boolean isFollowing = false;

    public LiftSubsystem(EncodedMotor<DcMotorEx> l){
        liftMotor = l;
    }

    public void setLiftPosition(double pos){
        pidController.setTargetPosition(pos);
        isFollowing = true;
    }

    public void liftToTop(){
        setLiftPosition(LIFT_UPPER_LIMIT);
    }

    public void liftToBottom(){
        setLiftPosition(LIFT_LOWER_LIMIT);
    }


    @Override
    public void periodic() {
        if (isFollowing) {
            liftMotor.setSpeed(pidController.update(liftMotor.get()));
            isFollowing = !isAtTarget();
        }
        else {
            liftMotor.setSpeed(0.0);
        }
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

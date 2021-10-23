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

    /**
     * @param EncodedMotor
     */
    public LiftSubsystem(EncodedMotor<DcMotorEx> l){
        liftMotor = l;
    }

    /**
     *
     * @param double to indicate target lift position
     */
    public void setLiftPosition(double pos){
        pidController.setTargetPosition(pos);
        isFollowing = true;
    }

    /**
     * set the lift to top, with the float constant LIFT_UPPER_LIMIT
     */
    public void liftToTop(){
        setLiftPosition(LIFT_UPPER_LIMIT);
    }

    /**
     * set the lift to bottom, with the float constant LIFT_LOWER_LIMIT, which is 0
     */
    public void liftToBottom(){
        setLiftPosition(LIFT_LOWER_LIMIT);
    }

    /**
     * basically update something every loop
     * which is let the motor running until receive signal about stopping
     */
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

    /**
     * @return true when motor position reached around target position
     * using something called dead-zone, so when the motor moved slightly over the target don't necessary go-back
     */
    public boolean isAtTarget(){
        return Math.abs(pidController.getTargetPosition() - liftMotor.get()) < DEADZONE;
    }

    /**
     * stop the pid controller
     * indirectly tells the periodic() method to stop update, which make the boolean isFollowing false
     */
    public void stop(){
        pidController.reset();
        isFollowing = false;
    }

    /**
     * @return motor position in double
     */
    @Override
    public Double get() {
        return liftMotor.get();
    }

}

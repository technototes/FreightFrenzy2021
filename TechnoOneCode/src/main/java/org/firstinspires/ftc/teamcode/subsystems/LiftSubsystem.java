package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Pose2d;
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
        public static double LIFT_UPPER_LIMIT = 750.0;
        public static double LIFT_LOWER_LIMIT = 0.0;
        public static double COLLECT = 0, LEVEL_1 = 100, LEVEL_2 = 350, LEVEL_3 = 700;

        public static double DEADZONE = 15;

        public static PIDCoefficients PID = new PIDCoefficients(0.005, 0, 0.0004);

    }
    public EncodedMotor<DcMotorEx> liftMotor;

    public PIDFController pidController;

    public LiftSubsystem(EncodedMotor<DcMotorEx> l){
        liftMotor = l;
        l.zeroEncoder();
        l.setOutputLimits(-0.3, 1);
        pidController = new PIDFController(PID, 0, 0, 0, (x,y)->0.05);
    }

    public void setLiftPosition(double pos){
        pidController.setTargetPosition(Range.clip(pos, LIFT_LOWER_LIMIT, LIFT_UPPER_LIMIT));
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
            liftMotor.setSpeed(pidController.update(liftMotor.get()));
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
    }

    /**
     * @return motor position in double
     */
    @Override
    public Double get() {
        return pidController.getTargetPosition();
    }

    public boolean isLifted(){
        return pidController.getTargetPosition()>10;
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class ArmSubsystem implements Subsystem, Supplier<Double> {
    public static class ArmConstant{
        public static double LIFT_UPPER_LIMIT = 1000;
        public static double LIFT_LOWER_LIMIT = 0;
        public static double DEADZONE = 0.1;

        public static double TOP_LEVEL = .5;
        public static double MIDDLE_LEVEL = .4;
        public static double BOTTOM_LEVEL = .3;
        public static double COLLECT = 100;
    }
    EncodedMotor<DcMotorEx> liftMotor;
    public static final PIDCoefficients PID = new PIDCoefficients(0.002, 0, 0);
    public PIDFController pidController;

    public ArmSubsystem(EncodedMotor<DcMotorEx> m){
        liftMotor = m;
        m.zeroEncoder();
        m.setOutputLimits(-0.5, 1);
        pidController = new PIDFController(PID, 0, 0, 0, (x, y)->0.1);
    }

    public void setPosition(double pos){
        pidController.setTargetPosition(Range.clip(pos, ArmConstant.LIFT_LOWER_LIMIT, ArmConstant.LIFT_UPPER_LIMIT));
    }

    public void goToTop(){
        setPosition(ArmConstant.LIFT_UPPER_LIMIT);
    }

    public void goToBot(){
        setPosition(ArmConstant.LIFT_LOWER_LIMIT);
    }

    public void stop(){
        pidController.reset();
    }

    public boolean isAtTarget(){
        return Math.abs(pidController.getTargetPosition() - liftMotor.get()) < ArmConstant.DEADZONE;
    }

    @Override
    public void periodic() {
        liftMotor.setSpeed(pidController.update(liftMotor.get()));
    }

    @Override
    public Double get() {
        return pidController.getTargetPosition();
    }
}

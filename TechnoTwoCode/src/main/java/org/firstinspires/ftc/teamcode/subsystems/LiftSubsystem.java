package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class LiftSubsystem implements Subsystem, Supplier<Double> {
    public EncodedMotor<DcMotorEx> liftMotor;
    public static final double LIFT_UPPER_LIMIT = 1000;
    public static final double LIFT_LOWER_LIMIT = 0;

    public LiftSubsystem(EncodedMotor<DcMotorEx> l){
        liftMotor = l;
    }

    public void setLiftPosition(double pos){
        liftMotor.setPosition(pos);
    }

    public void liftToTop(){
        setLiftPosition(LIFT_UPPER_LIMIT);
    }

    public void liftToBottom(){
        setLiftPosition(LIFT_LOWER_LIMIT);
    }

    @Override
    public Double get() {
        return liftMotor.getSensorValue();
    }
}

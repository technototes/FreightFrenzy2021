package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.*;

/**
 * Subsystem holding methods used for Intake commands. Intake will be responsible for bringing
 * freight in and out of our robot
 */
public class IntakeSubsystem implements Subsystem, Supplier<Double> {

    @Config
    public static class IntakeConstants{
        public static double INTAKE_IN_SPEED = 1;
        public static double INTAKE_OUT_SPEED = -1;
        public static double INTAKE_STOP_SPEED = 0;
    }

    public Motor<DcMotorEx> motor;

    public IntakeSubsystem(Motor<DcMotorEx> m){motor = m;}

    /**
     * Set the intake motor to run in at a constant speed
     */
    public void in(){
        motor.setSpeed(INTAKE_IN_SPEED);
    }

    /**
     * Set the intake motor to run out at a constant speed
     */
    public void out(){
        motor.setSpeed(INTAKE_OUT_SPEED);
    }

    /**
     * Set the intake motor to stop running
     */
    public void stop(){
        motor.setSpeed(INTAKE_STOP_SPEED);
    }
    @Override
    public Double get() {
        return motor.getSpeed();
    }
}

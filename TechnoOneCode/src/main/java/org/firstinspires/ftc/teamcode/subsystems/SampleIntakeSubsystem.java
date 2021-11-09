package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.RangeSensor;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.INTAKE_IN_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.INTAKE_OUT_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem.IntakeConstants.INTAKE_STOP_SPEED;
@SuppressWarnings("unused")
public class SampleIntakeSubsystem implements Subsystem {

    public static class IntakeConstants{
        public static double INTAKE_IN_SPEED = 1;
        public static double INTAKE_OUT_SPEED = -1;
        public static double INTAKE_STOP_SPEED = 0;
    }

    public Motor<DcMotorEx> motor;


    public SampleIntakeSubsystem(Motor<DcMotorEx> m){
        motor = m;
    }

    public void in(){
        motor.setSpeed(INTAKE_IN_SPEED);
    }

    public void out(){
        motor.setSpeed(INTAKE_OUT_SPEED);
    }

    public void stop(){
        motor.setSpeed(INTAKE_STOP_SPEED);
    }
}

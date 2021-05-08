package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.EncodedMotorGroup;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.motor.EncodedMotorSubsystem;
import com.technototes.logger.Stated;

/** Shooter subsystem
 *
 */
public class ShooterSubsystem extends EncodedMotorSubsystem implements Stated<Double> {
    public EncodedMotor<DcMotorEx> motor1;
    public Motor<DcMotorEx> motor2;
    public Servo flap;

    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(200, 0, 0, 15);

    public ShooterSubsystem(EncodedMotor<DcMotorEx> m1, Motor<DcMotorEx> m2, Servo f){
        super(new EncodedMotorGroup(m1, m2));
        motor1 = m1;
        motor2 = m2;
        m1.setPIDFCoeffecients(MOTOR_VELO_PID);
        flap = f;
        flap.setRange(0.5, 1);

    }
    public void setVelocity(double p){
        motor1.getDevice().setVelocity(p);
        motor2.getDevice().setPower(motor1.getDevice().getPower());

    }

    public double getVelocity(){
        return motor1.getDevice().getVelocity();
    }
    public double getIdleVelocity(){
        return 500; //idle speed here
    }
    public boolean isAtIdleVelocity(){
        return getIdleVelocity() <= getVelocity();
    }

    public void setFlapPosition(double pos){
        flap.setPosition(pos);
    }

    public double getFlapPosition(){
        return flap.getPosition();
    }


    @Override
    public Double getState() {
        return getVelocity();
    }


}

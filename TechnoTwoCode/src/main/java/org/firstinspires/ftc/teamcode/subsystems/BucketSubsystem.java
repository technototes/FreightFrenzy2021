package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.*;

import java.util.function.Supplier;

public class BucketSubsystem implements Subsystem, Supplier<Double> {
    public static class BucketConstant{
        /**
         * so called dead-zone
         */
        public static double TOLERANCE_ZONE = 0.1;

        public static double MOTOR_LOWER_LIMIT = 0;
        public static double MOTOR_UPPER_LIMIT = 100;

        /**
         * because this subsystem requires motor and servo cooperate together
         * motor [0] servo [1]
         */
        public static double[] COMBINATION_COLLECTING = {0, 0};
        public static double[] COMBINATION_COLLECTED = {0, 0};
        public static double[] COMBINATION_0 = {0, 0};
        public static double[] COMBINATION_45 = {0, 0};
        public static double[] COMBINATION_TOP = {0, 0};
        public static double[] COMBINATION_LEVEL3 = {0, 0};
        public static double[] COMBINATION_LEVEL2 = {0, 0};
        public static double[] COMBINATION_LEVEL1 = {0, 0};
        public static double[] COMBINATION_IDLE = {0, 0};
    }

    EncodedMotor<DcMotorEx> bucketMotor;
    Servo bucketServo;

    public double bucketServo_targetPosition;

    public static final PIDCoefficients pidCoefficients_motor = new PIDCoefficients(0.002, 0, 0);
    public PIDFController pidController_motor;

    public BucketSubsystem (EncodedMotor<DcMotorEx> motor, Servo servo) {
        this.bucketMotor = motor;
        this.bucketServo = servo;
        pidController_motor = new PIDFController(pidCoefficients_motor);
    }

    /**
     * using more detailed method name so you know what's you using
     * @param position
     */
    private void setMotorPosition(double pos){
        pidController_motor.setTargetPosition(Range.clip(pos, MOTOR_LOWER_LIMIT, MOTOR_UPPER_LIMIT));
    }
    private void setServoPosition(double pos){
        bucketServo.setPosition(pos);
        bucketServo_targetPosition = pos;
    }
    public void setPositionCombination(double motor_pos, double servo_pos){
        setMotorPosition(motor_pos);
        setServoPosition(servo_pos);
    }

    /**
     * @return true when motor position reached around target position
     * using something called dead-zone, so when the motor moved slightly over the target don't necessary go-back
     */
    private boolean isMotorAtTarget(){
        return Math.abs(pidController_motor.getTargetPosition() - bucketMotor.get()) < TOLERANCE_ZONE;
    }

    /**
     * @return true when motor position reached around target position
     * using something called dead-zone, so when the motor moved slightly over the target don't necessary go-back
     */
    private boolean isServoAtTarget(){
        return Math.abs(bucketServo_targetPosition - bucketServo.getPosition()) < TOLERANCE_ZONE;
    }

    /**
     * @return true when both motor and servo at target
     * calling their isAtTarget method
     */
    public boolean isAtTarget(){
        return isMotorAtTarget() && isServoAtTarget();
    }

    /**
     * get the target position of motor, but should be something else because this subsystem have another servo
     * @return double
     */
    @Override
    public Double get() {
        return pidController_motor.getTargetPosition();
    }

    /**
     * update the speed of bucketMotor
     */
    @Override
    public void periodic() {
        bucketMotor.setSpeed(pidController_motor.update(bucketMotor.get()));
    }

    /**
     * only need to stop the motor
     */
    public void stop(){
        pidController_motor.reset();
    }
}

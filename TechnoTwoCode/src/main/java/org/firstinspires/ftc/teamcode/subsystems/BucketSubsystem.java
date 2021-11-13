package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import static org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem.BucketConstant.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.Supplier;

public class BucketSubsystem implements Subsystem, Supplier<Double>, Loggable {
    public static class BucketConstant{
        /**
         * so called dead-zone
         */
        public static double TOLERANCE_ZONE = 0.1;

        public static double MOTOR_LOWER_LIMIT = -100;
        public static double MOTOR_UPPER_LIMIT = 100;
        public static double SERVO_LOWER_LIMIT = 0;
        public static double SERVO_UPPER_LIMIT = 100;

        public static PIDCoefficients pidCoefficients_motor = new PIDCoefficients(0.002, 0, 0);

        /**
         * because this subsystem requires motor and servo cooperate together
         * motor [0] servo [1]
         * feel free to add more constants if needed and pass them to setPositionCombination()
         * some constants just used for transition purpose, between intake and unload, because always want the bucket facing upward
         * for the motor position might need be negative number for up, invert the motor does not work
         *
         * here's the explanation to these constants:
         * COMBINATION_COLLECTING the bucket touch ground and the intake running
         * COMBINATION_COLLECTED the bucket have block inside, and tilt-up little so the arm ready to lifting
         * COMBINATION_0_DEGREE the arm is 180-degree leveled to the ground
         * COMBINATION_45_DEGREE the arm is 45-degree from the horizontal axis, but the bucket will tilt a little
         * COMBINATION_TOP the arm is 90-degree from the horizontal axis that forms a right-angle
         * COMBINATION_LEVEL3 unload the block to level 3 of the shelf
         * COMBINATION_LEVEL2 unload the block to level 2 of the shelf
         * COMBINATION_LEVEL1 unload the block to level 1 of the shelf
         *
         * for just bare servo
         * 0.25 is 45 degree
         * 0.5 is 135 degrees
         * 0.75 is 180 degrees
         * 1 is 270 degree
         */
        public static double[] COMBINATION_COLLECTING = {0, 0};
        public static double[] COMBINATION_COLLECTED = {-0.1, 0};
        public static double[] COMBINATION_0_DEGREE = {0, 0};
        public static double[] COMBINATION_45_DEGREE = {0, 0};
        public static double[] COMBINATION_TOP = {-0.45, 0};
        public static double[] COMBINATION_TOP_LEVEL = {-0.5, 0};
        public static double[] COMBINATION_MIDDLE_LEVEL = {-0.55, 0};
        public static double[] COMBINATION_BOTTOM_LEVEL = {-0.7, 0};
//        public static Combination COLLECT = new Combination(0, 0);
    }

    /**
     * something like enum might easier to configure in dash
     */
//    public static class Combination{
//        public double arm, bucket;
//        public Combination(double a, double s) {
//            arm = a;
//            bucket = s;
//        }
//        public double getArm(){
//            return arm;
//        }
//        public double getBucket(){
//            return bucket;
//        }
//    }
    @Log.Number (name = "Bucket motor")
    public EncodedMotor<DcMotorEx> bucketMotor;
    @Log.Number (name = "Bucket Servo")
    public Servo bucketServo;

    Telemetry telemetry;

    /**
     * serve isServoAtTarget() since the servo doesn't have a PID controller so need something to store the target position and to the comparison
     */
    public double bucketServo_targetPosition;

    public PIDFController pidController_motor;

    public BucketSubsystem (EncodedMotor<DcMotorEx> motor, Servo servo) {
        this.bucketMotor = motor;
        this.bucketServo = servo;
        pidController_motor = new PIDFController(pidCoefficients_motor, 0, 0, 0, (x, y)->0.05);
        this.bucketMotor.zeroEncoder();
    }

    public void setMotorPosition(double position){
        // This is the value to get our position to a 0-1 range
        double scale = (19.2*28*(108.0/20));
        pidController_motor.setTargetPosition(Range.clip(position, MOTOR_LOWER_LIMIT, MOTOR_UPPER_LIMIT)*scale);
    }
    public void setServoPosition(double position){
        bucketServo.setPosition(position);
        bucketServo_targetPosition = position;
    }
    public void setPositionCombination(double motor_pos, double servo_pos){
        setMotorPosition(motor_pos);
        setServoPosition(servo_pos);
    }

//    public void setCombination(Combination c){
//        setMotorPosition(c.getArm());
//        setServoPosition(c.getBucket());
//    }
    /**
     * just overloaded, so can simply throw in a constant
     * ideally the Command(s) will use this method with these pre-made constant(s)
     */
    public void setPositionCombination(double[] positionCombo){
        setPositionCombination(positionCombo[0], positionCombo[1]);
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
        bucketMotor.setSpeed(pidController_motor.update(bucketMotor.getEncoder().getPosition())*0.3);
        if (telemetry != null){
            telemetry.addLine(get().toString());
            telemetry.update();
        }
    }


    /**
     * only need to stop the motor
     */
    public void stop(){
        pidController_motor.reset();
    }
}

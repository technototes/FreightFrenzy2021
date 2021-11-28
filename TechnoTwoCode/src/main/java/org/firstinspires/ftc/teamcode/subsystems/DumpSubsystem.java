package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.*;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.Supplier;

public class DumpSubsystem implements Subsystem, Supplier<Double>, Loggable {
    public static class BucketConstant{
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
         * 0.25 is 45 degrees
         * 0.5 is 135 degrees
         * 0.75 is 180 degrees
         * 1 is 270 degrees
         */
        public static double BUCKET_COLLECT = 0.12;
        public static double BUCKET_CARRY = 0.3;
        public static double BUCKET_DUMP = 0.7;
        public static double BUCKET_TOP_LEVEL = 0.78;
        public static double BUCKET_MIDDLE_LEVEL = 0.78;
        public static double BUCKET_BOTTOM_LEVEL = 0.78;
    }

    public static class ArmConstant {
        public static final double ARM_COLLECT = 0;
        public static final double ARM_CARRY = -.2;
        public static final double ARM_TOP_LEVEL = -0.42;
        public static final double ARM_MIDDLE_LEVEL = -0.56;
        public static final double ARM_BOTTOM_LEVEL = -0.64;

        static final double MOTOR_LOWER_LIMIT = ARM_BOTTOM_LEVEL;
        static final double MOTOR_UPPER_LIMIT = ARM_COLLECT;

        static final PIDCoefficients pidCoefficients_motor = new PIDCoefficients(0.002, 0, 0);
        /**
         * so called dead-zone
         */
        static final double TOLERANCE_ZONE = 1;

        // This is the conversion factor from the motor position to a 0-1 range for a full circle
        static final double ARM_POSITION_SCALE = (19.2*28*(108.0/20));
    }

    @Log.Number (name = "Bucket motor")
    public EncodedMotor<DcMotorEx> bucketMotor;
    @Log.Number (name = "Bucket Servo")
    public Servo bucketServo;

    Telemetry telemetry;

    public PIDFController pidController_motor;

    public DumpSubsystem(EncodedMotor<DcMotorEx> motor, Servo servo) {
        this.bucketMotor = motor;
        this.bucketServo = servo;
        pidController_motor = new PIDFController(pidCoefficients_motor, 0, 0, 0, (x, y)->0.05);
        this.bucketMotor.zeroEncoder();
    }

    public void setMotorPosition(double position){
        pidController_motor.setTargetPosition(Range.clip(position, MOTOR_LOWER_LIMIT, MOTOR_UPPER_LIMIT) * ARM_POSITION_SCALE);
    }

    static double getScaledMotorPosition(double position) {
        return position / ARM_POSITION_SCALE;
    }

    public void dumpBucket() {
        bucketServo.setPosition(BucketConstant.BUCKET_DUMP);
    }

    public void setServoPosition(double position){
        // Servo position is controlled by dumpBucket() and the periodic() function. This method
        // now intentionally does nothing.
        //bucketServo.setPosition(position);
    }
    public void setPositionCombination(double motor_pos, double servo_pos){
        setMotorPosition(motor_pos);
        setServoPosition(servo_pos);
    }

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
    public boolean isMotorAtTarget(){
        return Math.abs(pidController_motor.getTargetPosition() - bucketMotor.get()) < TOLERANCE_ZONE;
    }

    /**
     * @return true when both motor and servo at target
     * calling their isAtTarget method
     */
    public boolean isAtTarget(){
        return isMotorAtTarget();
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
        double rawMotorPosition = bucketMotor.getEncoder().getPosition();
        bucketMotor.setSpeed(pidController_motor.update(rawMotorPosition)*0.65);
        if (telemetry != null){
            telemetry.addLine(get().toString());
            telemetry.update();
        }

        // Note: scaledMotorPosition is a negative number, hence the comparison operators look backwards!
        double scaledMotorPosition = getScaledMotorPosition(rawMotorPosition);
        double armCarryStart = (ArmConstant.ARM_CARRY - ArmConstant.ARM_COLLECT) / 2;
        if (scaledMotorPosition > (ArmConstant.ARM_CARRY + ArmConstant.ARM_TOP_LEVEL) / 2) {
            if (scaledMotorPosition <= armCarryStart) {
                bucketServo.setPosition(BucketConstant.BUCKET_CARRY);
            } else {
                double percentCarry = (scaledMotorPosition - ArmConstant.ARM_COLLECT) / (armCarryStart - ArmConstant.ARM_COLLECT);
                double bucketPosition = BucketConstant.BUCKET_COLLECT + percentCarry * (BucketConstant.BUCKET_CARRY - BucketConstant.BUCKET_COLLECT);
                bucketServo.setPosition(Range.clip(bucketPosition, BucketConstant.BUCKET_COLLECT, BucketConstant.BUCKET_CARRY));
            }
        }
    }

    /**
     * only need to stop the motor
     */
    public void stop(){
        pidController_motor.reset();
    }
}

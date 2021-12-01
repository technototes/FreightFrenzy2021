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

import kotlin.jvm.functions.Function2;

public class DumpSubsystem implements Subsystem, Supplier<Double>, Loggable {
    static class BucketConstant{
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
        static final double BUCKET_COLLECT = 0.1;
        static final double BUCKET_CARRY = 0.3;
        static final double BUCKET_DUMP = 0.7;

        // Range of values where the bucket should be in the carry position
        static final double ARM_CARRY_LIMIT_MIN = (ARM_COLLECT + ARM_CARRY) / 3;
        static final double ARM_CARRY_LIMIT_MAX = (ARM_CARRY + ARM_TOP_LEVEL) / 2;
    }

    public static class ArmConstant {
        // Note: these are in units of full circle (0 = start, -1 or 1 = full rotation)
        public static final double ARM_COLLECT = 0;
        public static final double ARM_CARRY = -.2;
        public static final double ARM_TOP_LEVEL = -0.42;
        public static final double ARM_MIDDLE_LEVEL = -0.56;
        public static final double ARM_BOTTOM_LEVEL = -0.64;
        static final double ARM_LEVEL_POSITION = -0.10; // Position of the level arm

        static final double MOTOR_LOWER_LIMIT = ARM_BOTTOM_LEVEL;
        static final double MOTOR_UPPER_LIMIT = ARM_COLLECT;

        static final PIDCoefficients pidCoefficients_motor = new PIDCoefficients(0.002, 0, 0);

        // The motor speed value required to keep the arm level to counteract gravity
        static final double MOTOR_GRAVITY_SPEED_FACTOR = -0.02;

        // This is the conversion factor from the motor position to a 0-1 range for a full circle
        static final double ARM_POSITION_SCALE = (19.2*28*(108.0/20));

        /**
         * so called dead-zone, in encoder ticks
         */
        static final double TOLERANCE_ZONE_TICKS = ARM_POSITION_SCALE / 360; // one degree
    }

    // These must be public for the logging functionality
    @Log.Number (name = "Bucket motor")
    public EncodedMotor<DcMotorEx> bucketMotor;
    @Log.Number (name = "Bucket Servo")
    public Servo bucketServo;
    @Log.Number (name = "Bucket speed")
    public double bucketSpeed = 0.0;
    long lastSpeedMeasureTimeMillis = 0;
    double lastBucketEncoderPosition = 0.0;

    Telemetry telemetry;

    PIDFController pidController_motor;

    // Maximum acceleration in absolute units of change of 'speed' per second squared
    static final double MAX_BUCKET_ACCELERATION = 4.0;

    // Cap measurement interval to avoid loop stalls letting the motor jump from 0 to 1
    static final double MAX_ACCELERATION_MEASUREMENT_SECS = 0.2;

    // Close enough to zero, for floating point numbers
    static final double EPSILON = 1e-6;

    public DumpSubsystem(EncodedMotor<DcMotorEx> motor, Servo servo) {
        this.bucketMotor = motor;
        this.bucketServo = servo;
        pidController_motor = new PIDFController(pidCoefficients_motor, 0, 0, 0);
        pidController_motor.setOutputBounds(-1.0, 1.0); // DC motor can't go beyond full speed
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

    /**
     * @return true when motor position reached around target position
     * using something called dead-zone, so when the motor moved slightly over the target don't necessary go-back
     */
    public boolean isMotorAtTarget(){
        return Math.abs(pidController_motor.getTargetPosition() - bucketMotor.get()) < TOLERANCE_ZONE_TICKS;
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
        // Set the bucket arm position
        double rawMotorPosition = bucketMotor.getEncoder().getPosition();
        long currentTimeMillis = System.currentTimeMillis();
        double deltaTimeSecs = (currentTimeMillis - lastSpeedMeasureTimeMillis) / 1000.0;
        double ticksPerSecond = (deltaTimeSecs > EPSILON) ? (rawMotorPosition - lastBucketEncoderPosition) / deltaTimeSecs : 0.0;
        double newSpeed = getAccelerationLimitedBucketSpeed(bucketSpeed, pidController_motor.update(rawMotorPosition, ticksPerSecond), deltaTimeSecs);
        lastSpeedMeasureTimeMillis = currentTimeMillis;
        bucketSpeed = newSpeed;
        bucketMotor.setSpeed(newSpeed);
        if (telemetry != null){
            telemetry.addLine(get().toString());
            telemetry.update();
        }

        // Set the bucket servo position based on the arm positions
        double bucketPosition = tryGetBucketPositionFromArm(getScaledMotorPosition(rawMotorPosition));
        if (!Double.isNaN(bucketPosition)) {
            bucketServo.setPosition(bucketPosition);
        }
    }

    static double getAccelerationLimitedBucketSpeed(double oldSpeed, double newSpeed, double deltaTimeSecs) {
        if (deltaTimeSecs > EPSILON) {
            deltaTimeSecs = Math.min(deltaTimeSecs, MAX_ACCELERATION_MEASUREMENT_SECS);
            double currentAcceleration = Math.abs(newSpeed - oldSpeed) / deltaTimeSecs;
            if (currentAcceleration > MAX_BUCKET_ACCELERATION) {
                newSpeed = oldSpeed + (deltaTimeSecs * MAX_BUCKET_ACCELERATION * ((newSpeed < oldSpeed) ? -1.0 : 1.0));
            }
        }

        return newSpeed;
    }

    static double gravityArmFeedForward(Double positionTicks, Double velocityTicksPerSec) {
        double positionRadians = ((positionTicks / ARM_POSITION_SCALE) - ARM_LEVEL_POSITION) * Math.PI * 2;
        return Math.cos(positionRadians) * MOTOR_GRAVITY_SPEED_FACTOR;
    }

    static double tryGetBucketPositionFromArm(double scaledMotorPosition) {
        // Note: scaledMotorPosition is a negative number, hence the comparison operators look backwards!
        if (scaledMotorPosition > BucketConstant.ARM_CARRY_LIMIT_MAX) {
            if (scaledMotorPosition <= BucketConstant.ARM_CARRY_LIMIT_MIN) {
                return BucketConstant.BUCKET_CARRY;
            } else {
                double percentCarry = (scaledMotorPosition - ArmConstant.ARM_COLLECT) / (BucketConstant.ARM_CARRY_LIMIT_MIN - ArmConstant.ARM_COLLECT);
                double bucketPosition = BucketConstant.BUCKET_COLLECT + percentCarry * (BucketConstant.BUCKET_CARRY - BucketConstant.BUCKET_COLLECT);
                return Range.clip(bucketPosition, BucketConstant.BUCKET_COLLECT, BucketConstant.BUCKET_CARRY);
            }
        }

        return Double.NaN;
    }

    /**
     * only need to stop the motor
     */
    public void stop(){
        pidController_motor.reset();
    }
}

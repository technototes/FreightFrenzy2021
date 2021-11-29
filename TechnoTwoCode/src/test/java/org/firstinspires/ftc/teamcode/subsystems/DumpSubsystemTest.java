package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.*;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.*;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.EPSILON;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.MAX_ACCELERATION_MEASUREMENT_SECS;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.MAX_BUCKET_ACCELERATION;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DumpSubsystemTest {

    @Test
    void getScaledMotorPosition() {
        assertEquals(0.0, DumpSubsystem.getScaledMotorPosition(0.0), EPSILON);
        assertEquals(1.0, DumpSubsystem.getScaledMotorPosition(ARM_POSITION_SCALE), EPSILON);
        assertEquals(-1.0, DumpSubsystem.getScaledMotorPosition(-ARM_POSITION_SCALE), EPSILON);
        assertEquals(0.5, DumpSubsystem.getScaledMotorPosition(ARM_POSITION_SCALE / 2), EPSILON);
        assertEquals(-0.5, DumpSubsystem.getScaledMotorPosition(-ARM_POSITION_SCALE / 2), EPSILON);
    }

    @Test
    void getAccelerationLimitedBucketSpeed() {
        assertEquals(0.0, DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, 0.0, 1.0), EPSILON);
        assertEquals(0.1, DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, 0.1, 1.0), EPSILON);
        assertEquals(0.2, DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, 0.2, 1.0), EPSILON);

        assertEquals(MAX_BUCKET_ACCELERATION * MAX_ACCELERATION_MEASUREMENT_SECS,
                DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, 1.0, MAX_ACCELERATION_MEASUREMENT_SECS * 2), EPSILON);
        assertEquals(-MAX_BUCKET_ACCELERATION * MAX_ACCELERATION_MEASUREMENT_SECS,
                DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, -1.0, MAX_ACCELERATION_MEASUREMENT_SECS * 2), EPSILON);
        assertEquals(MAX_BUCKET_ACCELERATION * MAX_ACCELERATION_MEASUREMENT_SECS,
                DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, 1.0, MAX_ACCELERATION_MEASUREMENT_SECS), EPSILON);
        assertEquals(-MAX_BUCKET_ACCELERATION * MAX_ACCELERATION_MEASUREMENT_SECS,
                DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, -1.0, MAX_ACCELERATION_MEASUREMENT_SECS), EPSILON);
        assertEquals(MAX_BUCKET_ACCELERATION * MAX_ACCELERATION_MEASUREMENT_SECS / 2,
                DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, 1.0, MAX_ACCELERATION_MEASUREMENT_SECS / 2), EPSILON);
        assertEquals(-MAX_BUCKET_ACCELERATION * MAX_ACCELERATION_MEASUREMENT_SECS / 2,
                DumpSubsystem.getAccelerationLimitedBucketSpeed(0.0, -1.0, MAX_ACCELERATION_MEASUREMENT_SECS / 2), EPSILON);
    }

    @Test
    void gravityArmFeedForward() {
        // Quarter way around the circle should have zero feed forward (straight up & straight down)
        assertEquals(0.0, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE - (ARM_POSITION_SCALE / 4), 0.0), EPSILON);
        assertEquals(0.0, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE + (ARM_POSITION_SCALE / 4), 0.0), EPSILON);

        // Straight forward and one full revolution should have max feed forward
        assertEquals(MOTOR_GRAVITY_SPEED_FACTOR, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE, 0.0), EPSILON);
        assertEquals(MOTOR_GRAVITY_SPEED_FACTOR, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE - ARM_POSITION_SCALE, 0.0), EPSILON);
        assertEquals(MOTOR_GRAVITY_SPEED_FACTOR, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE + ARM_POSITION_SCALE, 0.0), EPSILON);

        // Straight backward should have inverted feed forward
        assertEquals(-MOTOR_GRAVITY_SPEED_FACTOR, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE - (ARM_POSITION_SCALE / 2), 0.0), EPSILON);
        assertEquals(-MOTOR_GRAVITY_SPEED_FACTOR, DumpSubsystem.gravityArmFeedForward(ARM_LEVEL_POSITION * ARM_POSITION_SCALE + (ARM_POSITION_SCALE / 2), 0.0), EPSILON);
    }

    @Test
    void tryGetBucketPositionFromArm() {
        assertEquals(BUCKET_COLLECT, DumpSubsystem.tryGetBucketPositionFromArm(ARM_COLLECT), EPSILON);
        assertEquals(BUCKET_CARRY, DumpSubsystem.tryGetBucketPositionFromArm(ARM_CARRY), EPSILON);
        assertEquals(BUCKET_CARRY, DumpSubsystem.tryGetBucketPositionFromArm(ARM_CARRY_LIMIT_MIN - 0.01), EPSILON);
        assertEquals(BUCKET_CARRY, DumpSubsystem.tryGetBucketPositionFromArm(ARM_CARRY_LIMIT_MAX + 0.01), EPSILON);
        assertEquals(Double.NaN, DumpSubsystem.tryGetBucketPositionFromArm(ARM_CARRY_LIMIT_MAX - 0.01), EPSILON);
        assertEquals(Double.NaN, DumpSubsystem.tryGetBucketPositionFromArm(ARM_TOP_LEVEL));
        assertEquals(Double.NaN, DumpSubsystem.tryGetBucketPositionFromArm(ARM_MIDDLE_LEVEL));
        assertEquals(Double.NaN, DumpSubsystem.tryGetBucketPositionFromArm(ARM_BOTTOM_LEVEL));

        assertEquals((BUCKET_COLLECT + BUCKET_CARRY) / 2,
                DumpSubsystem.tryGetBucketPositionFromArm((ARM_COLLECT + ARM_CARRY_LIMIT_MIN) / 2), EPSILON);
    }
}
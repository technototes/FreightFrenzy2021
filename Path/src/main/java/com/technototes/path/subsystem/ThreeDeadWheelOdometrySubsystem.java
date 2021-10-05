package com.technototes.path.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.technototes.library.hardware.sensor.encoder.MotorEncoder;
import com.technototes.library.subsystem.Subsystem;


import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
public abstract class ThreeDeadWheelOdometrySubsystem extends ThreeTrackingWheelLocalizer implements Subsystem<MotorEncoder> {

    private MotorEncoder leftEncoder, rightEncoder, frontEncoder;

    private double lateralDistance, forwardOffset;

    public ThreeDeadWheelOdometrySubsystem(MotorEncoder l, MotorEncoder r, MotorEncoder f, double lateralDist, double forwardOff) {
        super(
                Arrays.asList(
                new Pose2d(0, lateralDist / 2, 0), // left
                new Pose2d(0, -lateralDist / 2, 0), // right
                new Pose2d(forwardOff, 0, Math.toRadians(90)) // front
        ));
        leftEncoder = l;
        rightEncoder = r;
        frontEncoder = f;

        lateralDistance = lateralDist;
        forwardOffset = forwardOff;
    }

    public double encoderTicksToInches(double ticks) {
        return getWheelRadius() * 2 * Math.PI * getGearRatio() * ticks / getTicksPerRev();
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCurrentPosition()),
                encoderTicksToInches(rightEncoder.getCurrentPosition()),
                encoderTicksToInches(frontEncoder.getCurrentPosition())
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        // TODO: If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method

        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCorrectedVelocity()),
                encoderTicksToInches(rightEncoder.getCorrectedVelocity()),
                encoderTicksToInches(frontEncoder.getCorrectedVelocity())
        );
    }

    public abstract double getTicksPerRev();

    public abstract double getWheelRadius();

    public double getGearRatio(){return 1;}
}

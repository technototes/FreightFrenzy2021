package com.technototes.path.subsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.technototes.library.hardware.sensor.IDistanceSensor;
import com.technototes.library.hardware.sensor.IGyro;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.subsystem.Subsystem;

import java.util.Map;

public class DistanceSensorLocalizer implements Localizer, Subsystem {


    private Pose2d poseEstimate;

    private double maxSensorDistance = 100;

    private final Map<IDistanceSensor, Pose2d> sensorMap;
    private final IGyro gyro;

    public DistanceSensorLocalizer(IGyro g, Map<IDistanceSensor, Pose2d> map){
        gyro = g;
        sensorMap = map;
        poseEstimate = new Pose2d();
    }


    @NonNull
    @Override
    public Pose2d getPoseEstimate() {
        return poseEstimate;
    }

    @Override
    public void setPoseEstimate(@NonNull Pose2d pose2d) {
        poseEstimate = pose2d;
    }

    @Nullable
    @Override
    public Pose2d getPoseVelocity() {
        return null;
    }

    @Override
    public void update() {
        double heading = gyro.gyroHeadingInRadians();
        double xAccumulator, yAccumulator;
        sensorMap.forEach((iDistanceSensor, pose2d) -> {
            pose2d = new Pose2d(pose2d.vec().rotated(heading), pose2d.getHeading()+heading);
            if(iDistanceSensor.getDistance()<maxSensorDistance){


            }
        });
    }

}

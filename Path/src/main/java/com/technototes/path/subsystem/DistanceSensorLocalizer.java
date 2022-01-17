package com.technototes.path.subsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.acmerobotics.roadrunner.util.Angle;
import com.technototes.library.hardware.sensor.IDistanceSensor;
import com.technototes.library.hardware.sensor.IGyro;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.MathUtils;
import com.technototes.path.geometry.ConfigurableVector;

import java.util.ArrayList;
import java.util.List;
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
        double heading = getHeading();
        double accumX = 0, accumY = 0;
        int totalX = 0, totalY = 0;
        for(Map.Entry<IDistanceSensor, Pose2d> entry : sensorMap.entrySet()){
            IDistanceSensor sensor = entry.getKey();
            Pose2d sensorPose = entry.getValue();
            double distance = sensor.getDistance();
            if(distance<maxSensorDistance && distance > 0.5) {
                sensorPose = new Pose2d(sensorPose.vec().rotated(heading), Angle.norm(sensorPose.getHeading() + heading));
                switch (MathUtils.closestTo(2*sensorPose.getHeading()/Math.PI,  0, 1, 2, 3, 4)){
                    case 0: case 4:
                        accumX+=72-sensorPose.getX()-Math.cos(sensorPose.getHeading())*distance;
                        totalX++;
                        break;
                    case 1:
                        accumY+=72-sensorPose.getY()-Math.sin(sensorPose.getHeading())*distance;
                        totalY++;
                        break;
                    case 2:
                        accumX-=72+sensorPose.getX()+Math.cos(sensorPose.getHeading())*distance;
                        totalX++;
                        break;
                    case 3:
                        accumY-=72+sensorPose.getY()+Math.sin(sensorPose.getHeading())*distance;
                        totalY++;
                        break;

                }
            }
        }
        poseEstimate = new Pose2d(totalX != 0 ? accumX/totalX : 0, totalY != 0 ? accumY/totalY : 0 , heading);


    }
    public double gyroOffset = 0;

    public void setGyroOffset(double v){
        gyroOffset = v;
    }

    public double getHeading(){
        return Angle.norm(gyro.gyroHeadingInRadians()-gyroOffset);
    }


    public Pose2d getSafePoseEstimate(Pose2d otherRef){
        Pose2d dif = otherRef.minus(poseEstimate);
        return new Pose2d(
                Math.abs(dif.getX()) < 10 ? poseEstimate.getX() : otherRef.getX(),
                Math.abs(dif.getY()) < 10 ? poseEstimate.getY() : otherRef.getY(),
                otherRef.getHeading());
    }
}

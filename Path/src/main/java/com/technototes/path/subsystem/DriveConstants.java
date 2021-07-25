package com.technototes.path.subsystem;

import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public interface DriveConstants {

    double DEFAULT_TICKS_PER_REV = 28,
            DEFAULT_MAX_RPM = 6000,
            DEFAULT_MAX_VELO = 30,
            DEFAULT_MAX_ACCEL = 30,
            DEFAULT_MAX_ANGLE_VELO = Math.toRadians(60),
            DEFAULT_MAX_ANGLE_ACCEL = Math.toRadians(60);

    double motorTicksPerRev();
    double motorMaxRPM();
    default boolean motorEncoders(){
        return false;
    }
    default PIDFCoefficients motorVeloPIDF(){
        if(motorVeloPID() == null) return null;
        return new PIDFCoefficients(motorVeloPID().p, motorVeloPID().i, motorVeloPID().d, getMotorVelocityF(motorMaxRPM() / 60 * motorTicksPerRev()));
    }
    default PIDCoefficients motorVeloPID(){
        return null;
    }

    double wheelRadius();
    double gearRatio();
    double trackWidth();

    default double kV(){
        return 1.0 / rpmToVelocity(motorMaxRPM());
    }

    default double kA(){
        return 0;
    }

    default double kStatic(){
        return 0;
    }

    double maxVelo();
    double maxAccel();
    double maxAngleVelo();
    double maxAngleAccel();


    default double encoderTicksToInches(double ticks) {
        return wheelRadius() * 2 * Math.PI * gearRatio() * ticks / motorTicksPerRev();
    }

    default double rpmToVelocity(double rpm) {
        return rpm * gearRatio() * 2 * Math.PI * wheelRadius() / 60.0;
    }

    default double getMotorVelocityF(double ticksPerSecond) {
        return 32767 / ticksPerSecond;
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotorGroup;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.path.subsystem.TankConstants;
import com.technototes.path.subsystem.TankDrivebaseSubsystem;

public class PhoenixDriveBase extends TankDrivebaseSubsystem {
    // TODO: Fill this in
    public static TankConstants constants;
    public PhoenixDriveBase (EncodedMotorGroup<DcMotorEx> left, EncodedMotorGroup<DcMotorEx> right, IMU i){
        super(left, right, i, constants, null);
    }
}

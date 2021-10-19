package org.firstinspires.ftc.samplecode.strafer.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.technototes.library.hardware.motor.EncodedMotor;

import com.technototes.library.subsystem.drivebase.MecanumDrivebaseSubsystem;
import com.technototes.library.logger.Loggable;

public class DrivebaseSubsystem extends MecanumDrivebaseSubsystem<EncodedMotor<DcMotor>> implements Loggable {
////    public enum
    public DrivebaseSubsystem(EncodedMotor<DcMotor> flMotor, EncodedMotor<DcMotor> frMotor, EncodedMotor<DcMotor> rlMotor, EncodedMotor<DcMotor> rrMotor) {
        super(flMotor, frMotor, rlMotor, rrMotor);
    }


}

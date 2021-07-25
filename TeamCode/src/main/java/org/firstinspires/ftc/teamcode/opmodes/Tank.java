package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.motor.MotorGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.subsystem.drivebase.TankDrivebaseSubsystem;

public class Tank extends CommandOpMode {
    Motor<DcMotorEx> l1, l2, r1, r2;

    TankDrivebaseSubsystem<Motor> subsystem;
    @Override
    public void uponInit() {
        l1 = new Motor<>("l1");
        l2 = new Motor<>("l2");
        r1 = new Motor<>("r1");
        r2 = new Motor<>("r2");

        subsystem = new TankDrivebaseSubsystem<>(new MotorGroup(l1, l2), new MotorGroup(r1, r2));

    }

    @Override
    public void runLoop() {
        subsystem.arcadeDrive(driverGamepad.leftStickY.getAsDouble(), driverGamepad.leftStickX.getAsDouble());
    }
}

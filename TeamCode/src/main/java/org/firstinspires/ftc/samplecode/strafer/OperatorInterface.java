package org.firstinspires.ftc.samplecode.strafer;

import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.control.gamepad.CommandGamepad;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;

public class OperatorInterface {
    public Robot robot;
    public CommandGamepad driverGamepad;

    public OperatorInterface(CommandGamepad g1, CommandGamepad g2, Robot r) {
        if(g1==null){
            System.out.println("reee");
        }
        driverGamepad = g1;
        robot = r;
        setDriverControls();
    }

    public void setDriverControls() {
        driverGamepad.a.whenPressed(new OldInstantCommand(() -> {
            robot.drivebaseSubsystem.flMotor.zeroEncoder();
            robot.drivebaseSubsystem.frMotor.zeroEncoder();
            robot.drivebaseSubsystem.rlMotor.zeroEncoder();
            robot.drivebaseSubsystem.rrMotor.zeroEncoder();
        }));
    }

}

package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="eeeeeee")
public class YeetOpMode extends CommandOpMode {
    public Robot robot;

    @Override
    public void uponInit() {
        robot = new Robot();

    }

    @Override
    public void runLoop() {
        robot.drivebaseSubsystem.setWeightedDrivePower(new Pose2d(1, 0, 0));
        sleep(3000);
        robot.drivebaseSubsystem.setWeightedDrivePower(new Pose2d(0, 0, 0));
        terminate();
    }
}

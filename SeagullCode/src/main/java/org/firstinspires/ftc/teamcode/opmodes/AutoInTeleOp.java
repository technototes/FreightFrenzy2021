package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "With Auto")
public class AutoInTeleOp extends LinearOpMode implements Loggable {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;


    private Gamepad gamepad;
    private Pose2d currentPoseEstimate;
    private boolean isButtonJustPressed = true;

    @Override
    public void runOpMode() throws InterruptedException {
        gamepad = gamepad1;
        hardware = new Hardware();
        robot = new Robot(hardware);
        controls = new Controls(new CommandGamepad(gamepad), robot);
        waitForStart();
        while (opModeIsActive()) {
            currentPoseEstimate = robot.drivebaseSubsystem.getPoseEstimate();

            if (gamepad.dpad_up){
                if (!this.isButtonJustPressed) {
                    this.isButtonJustPressed = true;
                    robot.drivebaseSubsystem.followTrajectoryAsync(robot.drivebaseSubsystem.trajectoryBuilder(currentPoseEstimate).forward(10).build());
                }
            }
            else {
                isButtonJustPressed = false;
            }

            telemetry.addData("Is Just Pressed", isButtonJustPressed);
            telemetry.addData("Pose Display", robot.drivebaseSubsystem.poseDisplay);
            telemetry.update();
        }
    }
}
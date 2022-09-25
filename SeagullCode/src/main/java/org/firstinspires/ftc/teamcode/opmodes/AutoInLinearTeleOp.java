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

@TeleOp(name = "Experiment 1")
public class AutoInLinearTeleOp extends LinearOpMode implements Loggable {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;


    private Gamepad gamepad;
    private Pose2d currentPoseEstimate;
    private Pose2d targetPose;
    private boolean isButtonJustPressed = true;

    public Pose2d getRandomTargetPose() {
        return new Pose2d(Math.random() * 72 - 36, Math.random() * 72 - 36, Math.random() * 2 * Math.PI);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            gamepad = gamepad1;
            hardware = new Hardware();
            robot = new Robot(hardware);
            controls = new Controls(new CommandGamepad(gamepad), robot, true);
            targetPose = getRandomTargetPose();

            waitForStart();

            while (opModeIsActive()) {
                robot.drivebaseSubsystem.update();
                currentPoseEstimate = robot.drivebaseSubsystem.getPoseEstimate();

                if (gamepad.dpad_up){
                    if (!this.isButtonJustPressed) {
                        this.isButtonJustPressed = true;
                        robot.drivebaseSubsystem.followTrajectoryAsync(robot.drivebaseSubsystem.trajectoryBuilder(currentPoseEstimate).lineToLinearHeading(targetPose).build());
                        robot.drivebaseSubsystem.update();
                    }
                }
                else {
                    isButtonJustPressed = false;
                }

                String currentPoseEstimateString = "";
                if (currentPoseEstimate != null) {
                    currentPoseEstimateString = currentPoseEstimate.getX() + ", " + currentPoseEstimate.getY() + ", " + currentPoseEstimate.getHeading();
                } else {
                    currentPoseEstimateString = "null";
                }

                telemetry.addData("Is Just Pressed", isButtonJustPressed);
                telemetry.addData("Pose Display", currentPoseEstimateString);
                telemetry.addData("Current Target Pose", targetPose.getX() + " " + targetPose.getY() + " " + targetPose.getHeading());
                telemetry.update();
            }
        }
        catch (NullPointerException e) {
            System.err.println("Null Pointer Exception");
            e.printStackTrace();
        }
        finally {
            System.err.println("Finally");
            System.out.println("Finally");
        }
    }
}
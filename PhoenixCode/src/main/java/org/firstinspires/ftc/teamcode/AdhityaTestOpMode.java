package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Pushbot: Teleop Tank", group = "Pushbot")
@Disabled
public class AdhityaTestOpMode extends OpMode {

    /* Declare OpMode members. */
    PhoenixBot robot = new PhoenixBot(); // use the class created to define a Pushbot's hardware
    double clawOffset = 0.0;                  // Servo mid position
    final double CLAW_SPEED = 0.02;                 // sets rate to move servo

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        robot.leftDrive.setPower(left);
        robot.rightDrive.setPower(right);

        // Use gamepad left & right Bumpers to open and close the claw
        if (gamepad1.right_bumper)
            clawOffset += CLAW_SPEED;
        else if (gamepad1.left_bumper)
            clawOffset -= CLAW_SPEED;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
        robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

        // Use gamepad buttons to move the arm up (Y) and down (A)
        if (gamepad1.y)
            robot.leftArm.setPower(robot.ARM_UP_POWER);
        else if (gamepad1.a)
            robot.leftArm.setPower(robot.ARM_DOWN_POWER);
        else
            robot.leftArm.setPower(0.0);

        // Send telemetry message to signify robot running;
        telemetry.addData("claw", "Offset = %.2f", clawOffset);
        telemetry.addData("left", "%.2f", left);
        telemetry.addData("right", "%.2f", right);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}



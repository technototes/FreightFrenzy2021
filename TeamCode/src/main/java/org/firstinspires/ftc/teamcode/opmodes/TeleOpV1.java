package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.hardware.HardwareDevice;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Color;
import com.technototes.logger.Log;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.OperatorInterface;
import org.firstinspires.ftc.teamcode.Robot;

/** Main OpMode
 *
 */
@TeleOp(name = "TeleOpV1")
public class TeleOpV1 extends CommandOpMode implements Loggable {
    /** The robot
     *
     */
    public Robot robot;
    /** The Operator interface
     *
     */
    public OperatorInterface operatorInterface;
    @Override
    public void uponInit() {
        CommandScheduler.resetScheduler();
        robot = new Robot();
        operatorInterface = new OperatorInterface(driverGamepad, codriverGamepad, robot);
        robot.turretSubsystem.raise();
    }



    @Override
    public boolean additionalInitConditions() {
        return getRuntime()>5;
    }

    @Override
    public void runLoop() {
        System.out.print(robot.shooterSubsystem.motor1.getDevice().getVelocity());
        System.out.print("               ");
        System.out.println(robot.shooterSubsystem.motor1.getDevice().getPower());
    }
}

package org.firstinspires.ftc.samplecode.clawbot.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.samplecode.clawbot.OperatorInterface;
import org.firstinspires.ftc.samplecode.clawbot.Robot;

@Disabled
@TeleOp(name = "Claw TeleOp")
public class ClawBotTeleOp extends CommandOpMode implements Loggable {
    public Robot robot;
    public OperatorInterface OI;

    @Override
    public void uponInit() {
        robot = new Robot();
        OI = new OperatorInterface(driverGamepad, robot);
    }

    @Override
    public void runLoop() {
        robot.drivebaseSubsystem.arcadeDrive(driverGamepad.leftStickY.getAsDouble(), driverGamepad.leftStickX.getAsDouble());
    }

}

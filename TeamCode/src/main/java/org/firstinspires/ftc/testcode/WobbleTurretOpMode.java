package org.firstinspires.ftc.testcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Log;
import com.technototes.logger.Loggable;
@TeleOp(name="wobbleturretopmode")
public class WobbleTurretOpMode extends CommandOpMode implements Loggable {
    @Log(name = "claw")
    public Servo claw;
    @Log(name = "turret")
    public Servo turret;
    @Log(name = "arm1")
    public Servo left;
    @Log(name = "arm2")
    public Servo right;

    @Override
    public void uponInit() {
        claw = new Servo("wobbleClaw").setRange(0.1, 0.6);
        turret = new Servo("wobbleTurret").setRange(0, 0.47);
        left = new Servo("wobbleLeft").setRange(0.2, 0.55);
        right = new Servo("wobbleRight").setRange(0.45, 0.8);
        left.setPosition(1);
        right.setPosition(0);

        driverGamepad.dpadUp.whenPressed(new OldInstantCommand(()->left.setPosition(1)))
                .whenPressed(new OldInstantCommand(()->right.setPosition(0)));

        driverGamepad.dpadDown.whenPressed(new OldInstantCommand(()->left.setPosition(0)))
                .whenPressed(new OldInstantCommand(()->right.setPosition(1)));

        driverGamepad.dpadLeft.whenPressed(new OldInstantCommand(()-> claw.setPosition(0)));
        driverGamepad.dpadRight.whenPressed(new OldInstantCommand(()-> claw.setPosition(1)));

        driverGamepad.leftBumper.whenPressed(new OldInstantCommand(()-> turret.setPosition(0)));
        driverGamepad.rightBumper.whenPressed(new OldInstantCommand(()-> turret.setPosition(1)));

        driverGamepad.a.whenPressed(new WobbleInterpolateCommand(left, right));
    }
}

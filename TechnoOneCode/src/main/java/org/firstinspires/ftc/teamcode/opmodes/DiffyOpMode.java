package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.structure.CommandOpMode;
@TeleOp
@Disabled
public class DiffyOpMode extends CommandOpMode {
    public Servo left, right;
    public double x =0.2, r =0.2;

    @Override
    public void uponInit() {
        //0 to .45
        left = new Servo("left").invert();
        right = new Servo("right");
//        driverGamepad.dpadUp.whenPressed(()-> x +=0.05);
//        driverGamepad.dpadDown.whenPressed(()-> x -=0.05);
//        driverGamepad.dpadLeft.whenPressed(()-> r +=0.01);
//        driverGamepad.dpadRight.whenPressed(()-> r -=0.01);
        driverGamepad.a.whenPressed(()->{
            x = 0.1;
            r = 0.07;
        });
        driverGamepad.b.whenPressed(()->{
            x = 0.4;
            r = -0.03;
        });
    }
    @Override
    public void runLoop() {
        //L .43 R .37
        //L .07 r .13
        x = Range.clip(x, 0.1, 0.4);
        r = Range.clip(r, -0.1, 0.1);

        left.setPosition(x+r);
        right.setPosition(x-r);
        System.out.println("left: "+ x +", right: "+ r);
        //leftValue = driverGamepad.leftStickY.getAsDouble()-(driverGamepad.leftStickY.getAsDouble());
    }
}

package org.firstinspires.ftc.testcode;

import com.technototes.library.command.Command;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.hardware.servo.Servo;

public class WobbleInterpolateCommand extends WaitCommand {
    public Servo left, right;
    public WobbleInterpolateCommand(Servo l, Servo r){
        super(1);
        left = l;
        right = r;
    }

    @Override
    public void execute() {
        double val = commandRuntime.seconds();
        left.setPosition(val);
        right.setPosition(1-val);
    }
}

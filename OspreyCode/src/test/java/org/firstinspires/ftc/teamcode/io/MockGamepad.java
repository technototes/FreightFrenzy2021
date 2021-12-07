package org.firstinspires.ftc.teamcode.io;

import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class MockGamepad extends Gamepad {
    public abstract void map();
    public void update(){
        map();
        updateButtonAliases();
    }
    public void emptyMap(){
        a = false;
        b = false;
        x = false;
        y = false;
        dpad_up = false;
        dpad_down = false;
        dpad_left = false;
        dpad_right = false;
        start = false;
        back = false;
        left_bumper = false;
        left_stick_button = false;
        right_bumper = false;
        right_stick_button = false;
        left_trigger = 0;
        right_trigger = 0;
        left_stick_x = 0;
        left_stick_y = 0;
        right_stick_x = 0;
        right_stick_y = 0;
    }
}

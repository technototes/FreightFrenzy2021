package org.firstinspires.ftc.teamcode.io;

import android.inputmethodservice.Keyboard;
import android.text.method.KeyListener;
import android.view.KeyEvent;

import java.util.Scanner;

public class MockGamepadManager {
    public MockGamepad driver, codriver;
    public Thread gamepadThread;
    public MockGamepadManager(){
        driver = new MockGamepad() {
            @Override
            public void map() {
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
        };
        codriver = new MockGamepad() {
            @Override
            public void map() {
                //not bound so
               emptyMap();
            }
        };
        gamepadThread = new Thread(){
            @Override
            public void run() {
                driver.update();
                codriver.update();
            }
        };
    }
}

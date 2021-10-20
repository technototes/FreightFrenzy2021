package org.firstinspires.ftc.teamcode;

import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;

public class Controls {
    public CommandGamepad gamepad;
    public CommandButton dumpButton;
    public CommandButton retractButton;
    public CommandButton extendButton;
    public Controls(CommandGamepad g){
        gamepad = g;

        dumpButton = gamepad.a;
        retractButton = gamepad.b;
        extendButton = gamepad.x;
    }

    public void bindDepositControls(){

    }
}

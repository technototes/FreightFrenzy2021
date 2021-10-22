package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "TeleOp")
@SuppressWarnings("unused")
public class TeleOpMode extends CommandOpMode implements Loggable {
    public Robot robot;
    public Controls controls;

    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();
        controls = new Controls(driverGamepad, robot);
    }
}

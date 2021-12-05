package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.SingleDriverControls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConstants;

@TeleOp(name = "RedTeleOp")
@SuppressWarnings("unused")
public class RedTeleOp extends CommandOpMode implements Loggable {
    public Robot robot;
    public SingleDriverControls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        RobotConstants.updateAlliance(Alliance.RED);
        hardware = new Hardware();
        robot = new Robot(hardware);
        controls = new SingleDriverControls(robot, driverGamepad, codriverGamepad);
    }

}

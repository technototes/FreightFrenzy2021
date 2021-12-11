package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.ExpandedControls;
import org.firstinspires.ftc.teamcode.BaseControls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConstants;

@TeleOp(name = "BlueTeleOp")
@SuppressWarnings("unused")
public class BlueTeleOp extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;
    public BaseControls controls;
    @Override
    public void uponInit() {
        //MAYBE THIS WORKS
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        RobotConstants.updateAlliance(Alliance.BLUE);
        hardware = new Hardware();
        robot = new Robot(hardware);
        controls = new ExpandedControls(robot, driverGamepad, codriverGamepad);
    }

}

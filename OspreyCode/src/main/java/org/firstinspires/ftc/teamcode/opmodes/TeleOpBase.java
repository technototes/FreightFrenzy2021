package org.firstinspires.ftc.teamcode.opmodes;

import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.BaseControls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConstants;

public abstract class TeleOpBase extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;
    public BaseControls controls;

    @Override
    public void uponInit() {
        setup();
        hardware = new Hardware();
        robot = new Robot(hardware);
        controls = new BaseControls(robot, driverGamepad, codriverGamepad);
    }

    public abstract void setup();

    public @interface Config{
        Alliance alliance() default Alliance.RED;
        RobotConstants.AllianceHubStrategy allianceHubStrategy() default RobotConstants.AllianceHubStrategy.HIGH;

    }

}

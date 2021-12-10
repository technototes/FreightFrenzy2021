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
public class BlueTeleOp extends TeleOpBase {

    @Override
    public void setup() {
        RobotConstants.updateAlliance(RobotConstants.Strategy.HIGH_ALLIANCE);
    }
}

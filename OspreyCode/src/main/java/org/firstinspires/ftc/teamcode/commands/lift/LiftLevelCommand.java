package org.firstinspires.ftc.teamcode.commands.lift;

import static org.firstinspires.ftc.teamcode.RobotConstants.AllianceHubStrategy.HIGH;
import static org.firstinspires.ftc.teamcode.RobotConstants.getAllianceStrategy;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftConstants.LEVEL_2;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftConstants.LEVEL_3;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftLevelCommand extends LiftCommand {
    public LiftLevelCommand(LiftSubsystem ls) {
        super(ls, getAllianceStrategy() == HIGH ? LEVEL_3 : LEVEL_2);
    }
}

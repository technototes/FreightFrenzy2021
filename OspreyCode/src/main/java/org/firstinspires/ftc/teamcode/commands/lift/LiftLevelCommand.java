package org.firstinspires.ftc.teamcode.commands.lift;

import static org.firstinspires.ftc.teamcode.RobotConstants.AllianceHubStrategy.HIGH;
import static org.firstinspires.ftc.teamcode.RobotConstants.AllianceHubStrategy.MID;
import static org.firstinspires.ftc.teamcode.RobotConstants.getAllianceStrategy;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftLevelCommand extends ChoiceCommand {
    public LiftLevelCommand(LiftSubsystem ls) {
        super(new Pair<>(()->getAllianceStrategy() == HIGH, new LiftLevel3Command(ls)),
                new Pair<>(()->getAllianceStrategy() == MID, new LiftLevel2Command(ls)));
    }
}

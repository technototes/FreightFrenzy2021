package org.firstinspires.ftc.teamcode.commands.extension;

import static org.firstinspires.ftc.teamcode.RobotConstants.SharedHubStrategy.OWN;
import static org.firstinspires.ftc.teamcode.RobotConstants.SharedHubStrategy.STEAL;
import static org.firstinspires.ftc.teamcode.RobotConstants.getAlliance;
import static org.firstinspires.ftc.teamcode.RobotConstants.getSharedStrategy;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.LEFT;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.SHARED;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.STEAL_SHARED;
import static org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem.ExtensionConstants.RIGHT;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;

public class ExtensionSideCommand extends ChoiceCommand {
    public ExtensionSideCommand(ExtensionSubsystem subsystem) {
        super(new Pair<>(()->getSharedStrategy() == OWN, new ExtensionOutCommand(subsystem, SHARED, getAlliance().selectOf(RIGHT, LEFT))),
                new Pair<>(()->getSharedStrategy() == STEAL, new ExtensionOutCommand(subsystem, STEAL_SHARED,  getAlliance().selectOf(LEFT, RIGHT))));
    }
}

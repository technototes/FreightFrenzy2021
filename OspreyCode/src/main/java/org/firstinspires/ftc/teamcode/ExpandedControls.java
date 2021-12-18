package org.firstinspires.ftc.teamcode;


import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.CommandInput;
import com.technototes.library.util.Enablable;

import org.firstinspires.ftc.teamcode.commands.arm.BucketDumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionTranslateCommand;

public class ExpandedControls extends BaseControls implements Enablable<ExpandedControls> {

    //more adj buttons
    public CommandButton slideTranslateOutButton, slideTranslateInButton;

    public CommandAxis secondDumpAxis, secondIntakeButton;
    //strategy buttons
    public CommandInput sharedButton, stealButton, highButton, midButton, defenseButton;

    public ExpandedControls(Robot r, CommandGamepad driver, CommandGamepad codriver) {
        super(r, driver, codriver, false);

        liftAdjustUpButton = codriverGamepad.dpadUp;
        liftAdjustDownButton = codriverGamepad.dpadDown;
        slideTranslateInButton = codriverGamepad.dpadLeft;
        slideTranslateOutButton = codriverGamepad.dpadRight;
        turretAdjustLeftButton = codriverGamepad.leftBumper;
        turretAdjustRightButton = codriverGamepad.rightBumper;

        secondDumpAxis = codriverGamepad.leftTrigger;
        secondIntakeButton = codriverGamepad.rightTrigger;

        sharedButton = codriverGamepad.square;
        stealButton = codriverGamepad.cross;
        highButton = codriverGamepad.triangle;
        midButton = codriverGamepad.circle;
        defenseButton = codriver.start;

        sharedHubButton.disable();

        bindControls();
        bindStrategyControls();
        disable();
    }

    @Override
    public void bindExtensionControls() {
        super.bindExtensionControls();
        slideTranslateInButton.whilePressed(new ExtensionTranslateCommand(robot.extensionSubsystem, 0.05));
        slideTranslateOutButton.whilePressed(new ExtensionTranslateCommand(robot.extensionSubsystem, -0.05));
    }

    @Override
    public void bindArmControls() {
        super.bindArmControls();
        secondDumpAxis.whilePressedOnce(new BucketDumpVariableCommand(robot.armSubsystem, secondDumpAxis));
    }

    public void bindStrategyControls() {
        allianceHubButton.whenPressed(this::enable);
        toIntakeButton.whenPressed(this::disable);
        secondIntakeButton.whenPressed(this::disable);
//        sharedButton.whenPressed(() -> updateStrategy(RobotConstants.Strategy.SHARED));
//        stealButton.whenPressed(() -> updateStrategy(RobotConstants.Strategy.STEAL_SHARED));
//        highButton.whenPressed(() -> updateStrategy(RobotConstants.Strategy.HIGH_ALLIANCE));
//        midButton.whenPressed(() -> updateStrategy(RobotConstants.Strategy.MID_ALLIANCE));
//        defenseButton.whenPressed(() -> updateStrategy(RobotConstants.Strategy.DEFENDING));
    }



    //enabling stuff so vvv cool
    public boolean enabled = false;

    @Override
    public ExpandedControls enable() {
        codriverGamepad.rumbleBlips(2);
        driverGamepad.rumble(0.5);
        return Enablable.super.enable();

    }

    public ExpandedControls disable() {
        codriverGamepad.rumble(0.5);
        driverGamepad.rumbleBlips(2);
        return Enablable.super.disable();
    }

    @Override
    public ExpandedControls setEnabled(boolean enable) {
        liftAdjustUpButton.setEnabled(enable);
        liftAdjustDownButton.setEnabled(enable);
        slideTranslateOutButton.setEnabled(enable);
        slideTranslateInButton.setEnabled(enable);
        turretAdjustLeftButton.setEnabled(enable);
        turretAdjustRightButton.setEnabled(enable);
        secondDumpAxis.setEnabled(enable);
        secondIntakeButton.setEnabled(enable);
        enabled = enable;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
package org.firstinspires.ftc.teamcode;

import com.technototes.library.control.Binding;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandBinding;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.CommandInput;
import com.technototes.library.util.Enablable;

import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmSharedCommand;
import org.firstinspires.ftc.teamcode.commands.arm.BucketDumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel2Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

import java.util.function.Function;

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

        toIntakeButton = new CommandBinding(Binding.Type.SOME_ACTIVE, toIntakeButton, secondIntakeButton);


        bindControls();
        bindStrategyControls();
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
        sharedButton.whenPressed(() -> setStrategy(Strategy.SHARED));
        stealButton.whenPressed(() -> setStrategy(Strategy.STEAL_SHARED));
        highButton.whenPressed(() -> setStrategy(Strategy.HIGH_ALLIANCE));
        midButton.whenPressed(() -> setStrategy(Strategy.MID_ALLIANCE));
        defenseButton.whenPressed(() -> setStrategy(Strategy.DEFENDING));
    }

    public void setStrategy(Strategy s) {
        liftCommand = s.getLiftCommand(robot);
        armCommand = s.getArmCommand(robot);
        extensionCommand = s.getExtensionCommand(robot);
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

    public enum Strategy {
        SHARED(ArmSharedCommand::new, ExtensionRightSideCommand::new, ExtensionLeftSideCommand::new, LiftSharedCommand::new),
        STEAL_SHARED(ArmSharedCommand::new, ExtensionLeftOutCommand::new, ExtensionRightOutCommand::new, LiftSharedCommand::new),
        HIGH_ALLIANCE(ArmAllianceCommand::new, ExtensionOutCommand::new, LiftLevel3Command::new),
        MID_ALLIANCE(ArmAllianceCommand::new, ExtensionOutCommand::new, LiftLevel2Command::new),
        DEFENDING(ArmInCommand::new, ExtensionCollectCommand::new, LiftCollectCommand::new);
        Function<ArmSubsystem, ArmCommand> armCommand;
        Function<ExtensionSubsystem, ExtensionCommand> redExtensionCommand, blueExtensionCommand;
        Function<LiftSubsystem, LiftCommand> liftCommand;

        Strategy(Function<ArmSubsystem, ArmCommand> arm, Function<ExtensionSubsystem, ExtensionCommand> red, Function<ExtensionSubsystem, ExtensionCommand> blue, Function<LiftSubsystem, LiftCommand> lift) {
            armCommand = arm;
            redExtensionCommand = red;
            blueExtensionCommand = blue;
            liftCommand = lift;
        }

        Strategy(Function<ArmSubsystem, ArmCommand> arm, Function<ExtensionSubsystem, ExtensionCommand> extension, Function<LiftSubsystem, LiftCommand> lift) {
            armCommand = arm;
            redExtensionCommand = extension;
            blueExtensionCommand = extension;
            liftCommand = lift;
        }

        public ArmCommand getArmCommand(Robot r) {
            return armCommand.apply(r.armSubsystem);
        }

        public ExtensionCommand getExtensionCommand(Robot r) {
            return RobotConstants.getAlliance().selectOf(redExtensionCommand, blueExtensionCommand).apply(r.extensionSubsystem);
        }

        public LiftCommand getLiftCommand(Robot r) {
            return liftCommand.apply(r.liftSubsystem);
        }
    }
}
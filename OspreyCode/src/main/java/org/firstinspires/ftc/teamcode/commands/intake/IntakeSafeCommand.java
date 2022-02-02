package org.firstinspires.ftc.teamcode.commands.intake;

import org.firstinspires.ftc.teamcode.RobotState;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand extends IntakeInCommand {
    public IntakeSafeCommand(IntakeSubsystem s) {
        super(s);
    }

    @Override
    public boolean isFinished() {
        return RobotState.hasFreight() && getRuntime().seconds()>0.2;
    }

    @Override
    public void end(boolean cancel) {
        if(!cancel) subsystem.idle();
    }
}

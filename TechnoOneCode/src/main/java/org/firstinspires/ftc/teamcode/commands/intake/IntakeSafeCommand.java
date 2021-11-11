package org.firstinspires.ftc.teamcode.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand implements Command {
    IntakeSubsystem intake;
    DepositSubsystem deposit;

    public IntakeSafeCommand(IntakeSubsystem s, DepositSubsystem d){
        this(s, d, true);
    }
    public IntakeSafeCommand(IntakeSubsystem s, DepositSubsystem d, boolean require){
        intake = s;
        deposit = d;
        if(require) addRequirements(s, d);
    }

    @Override
    public void execute() {
        intake.in();
    }

    @Override
    public boolean isFinished() {
        return intake.isNearTarget();
    }

    @Override
    public void end(boolean cancel) {
        if(!cancel){
            intake.stop();
            deposit.carry();
        }
    }
}

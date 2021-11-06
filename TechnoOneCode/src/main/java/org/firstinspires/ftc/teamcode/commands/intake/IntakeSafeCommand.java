package org.firstinspires.ftc.teamcode.commands.intake;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand implements Command {
    IntakeSubsystem intake;
    DepositSubsystem deposit;

    public IntakeSafeCommand(IntakeSubsystem s, DepositSubsystem d){
        intake = s;
        deposit = d;
        addRequirements(s, d);
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

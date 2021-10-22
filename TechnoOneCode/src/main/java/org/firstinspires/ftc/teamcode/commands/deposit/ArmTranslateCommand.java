package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

public class ArmTranslateCommand implements Command {
    public DepositSubsystem subsystem;
    public double amount;
    public ArmTranslateCommand(DepositSubsystem s, double amt){
        subsystem = s;
        amount = amt;
        addRequirements(s);
    }
    @Override
    public void execute() {
        subsystem.translateExtension(amount);
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.1;
    }
}

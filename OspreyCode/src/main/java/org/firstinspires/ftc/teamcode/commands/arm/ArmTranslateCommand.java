package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmTranslateCommand implements Command {
    public ArmSubsystem subsystem;
    public double amount;
    public ArmTranslateCommand(ArmSubsystem s, double amt){
        subsystem = s;
        amount = amt;
        addRequirements(s);
    }

    @Override
    public void initialize() {
        subsystem.translateArm(amount);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.2;
    }
}

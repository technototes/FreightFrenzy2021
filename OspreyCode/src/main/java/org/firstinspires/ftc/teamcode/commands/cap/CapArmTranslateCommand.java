
package org.firstinspires.ftc.teamcode.commands.cap;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.CapSubsystem;

public class CapArmTranslateCommand implements Command {
    public CapSubsystem subsystem;
    public double amount;
    public CapArmTranslateCommand(CapSubsystem cap, double amt){
        subsystem = cap;
        addRequirements(cap);
        amount = amt;
    }

    @Override
    public void initialize() {
            subsystem.translateArm(amount);
    }

    @Override
    public void execute(){
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds()>0.05;
    }
}

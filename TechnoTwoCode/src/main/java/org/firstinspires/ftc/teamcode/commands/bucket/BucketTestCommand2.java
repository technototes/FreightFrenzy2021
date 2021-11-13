package org.firstinspires.ftc.teamcode.commands.bucket;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.BucketSubsystem;

public class BucketTestCommand2 implements Command {
    private BucketSubsystem subsystem;
    public BucketTestCommand2(BucketSubsystem s){
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
//        subsystem.setPositionCombination(0, 1.0);
        subsystem.setServoPosition(1);

        // subsystem.setMotorPosition(-0.46);
    }

    @Override
    public boolean isFinished() {
        return false;
    }



}

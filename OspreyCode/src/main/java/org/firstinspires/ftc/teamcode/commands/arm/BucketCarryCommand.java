package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class BucketCarryCommand implements Command {
    public ArmSubsystem depositSubsystem;
    public BucketCarryCommand(ArmSubsystem s){
        depositSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        depositSubsystem.setDump(ArmSubsystem.ArmConstants.CARRY);
    }
}

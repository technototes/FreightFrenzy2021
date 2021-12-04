package org.firstinspires.ftc.teamcode.commands.intake;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_CARRY;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand implements Command {
    IntakeSubsystem intake;
    DumpSubsystem dump;

    public IntakeSafeCommand(IntakeSubsystem s){
        this(s, true);
    }
    public IntakeSafeCommand(IntakeSubsystem s, boolean require){
        intake = s;
        if(require) addRequirements(s);
    }

    @Override
    public void execute(){
        intake.in();
    }

    @Override
    public boolean isFinished(){
        return intake.isNearTarget();
    }

    @Override
    public void end(boolean cancel){
        if(!cancel){
            intake.stop();
            dump.setMotorPosition(ARM_CARRY);
        }
    }
}

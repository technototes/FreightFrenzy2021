package org.firstinspires.ftc.teamcode.commands.intake;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_CARRY;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand implements Command {
    IntakeSubsystem intake;
    DumpSubsystem dump;

    public IntakeSafeCommand(IntakeSubsystem s, DumpSubsystem t){
        this(s, t, true);
    }
    public IntakeSafeCommand(IntakeSubsystem s, DumpSubsystem t, boolean require){
        intake = s;
        dump = t;
        if(require) addRequirements(s, t);
    }

    @Override
    public void initialize() {
        if (intake.getState() == IntakeSubsystem.State.IN) {
            intake.stop();
        } else {
            intake.in();
        }
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished(){
        return intake.hasCargo();
    }

    @Override
    public void end(boolean cancel){
        if(!cancel){
            intake.stop();
            dump.setMotorPosition(ARM_CARRY);
        }
    }
}

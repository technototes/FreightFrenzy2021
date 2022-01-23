package org.firstinspires.ftc.teamcode.commands.intake;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_CARRY;

import com.technototes.library.command.Command;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeSafeCommand implements Command {
    IntakeSubsystem intake;
    DumpSubsystem dump;

    public IntakeSafeCommand(IntakeSubsystem s, DumpSubsystem t, CommandGamepad g){
        this(s, t, g, true);
    }
    public IntakeSafeCommand(IntakeSubsystem s, DumpSubsystem t, CommandGamepad g, boolean require){
        intake = s;
        dump = t;
        this.intake.setGamepad(g);
        if(require) addRequirements(s, t);
    }

    @Override
    public void initialize() {
        if (intake.getState() == IntakeSubsystem.State.IN) {
            intake.out();
            new WaitCommand(0.5);
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
            intake.out();
            new WaitCommand(0.5);
            intake.stop();
            dump.setMotorPosition(ARM_CARRY);
        }
    }
}

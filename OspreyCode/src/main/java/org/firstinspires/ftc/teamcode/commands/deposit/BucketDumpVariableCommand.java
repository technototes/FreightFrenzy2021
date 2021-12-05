package org.firstinspires.ftc.teamcode.commands.deposit;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;

import java.util.function.DoubleSupplier;

import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.*;

public class BucketDumpVariableCommand implements Command {
    public DepositSubsystem subsystem;
    public DoubleSupplier supplier;
    public BucketDumpVariableCommand(DepositSubsystem s, DoubleSupplier pos){
        subsystem = s;
        supplier = pos;
        addRequirements(s);
    }
    public BucketDumpVariableCommand(DepositSubsystem s, double pos){
        this(s, ()->pos);
    }

    @Override
    public void execute() {
        subsystem.setDump(((supplier.getAsDouble()-0.2)*(DUMP-AUTO_CARRY))+AUTO_CARRY);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.setDump(AUTO_CARRY);
    }
}

package org.firstinspires.ftc.teamcode.commands.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

import static org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem.ArmConstants.*;

public class BucketDumpVariableCommand implements Command {
    public ArmSubsystem subsystem;
    public DoubleSupplier supplier;
    public BucketDumpVariableCommand(ArmSubsystem s, DoubleSupplier pos){
        subsystem = s;
        supplier = pos;
        addRequirements(s);
    }
    public BucketDumpVariableCommand(ArmSubsystem s, double pos){
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

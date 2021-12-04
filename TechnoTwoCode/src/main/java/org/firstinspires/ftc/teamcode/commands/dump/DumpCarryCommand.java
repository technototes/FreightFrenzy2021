package org.firstinspires.ftc.teamcode.commands.dump;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_CARRY;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCarryCommand implements Command {
    final DumpSubsystem dumpSys;

    public DumpCarryCommand(DumpSubsystem dump) {
        this.dumpSys = dump;
        addRequirements(dump);
    }

    @Override
    public void execute() {
        dumpSys.setMotorPosition(ARM_CARRY);
    }

    @Override
    public boolean isFinished() {
        return dumpSys.isMotorAtTarget() || getRuntime().seconds() > 1.0;
    }
}

package org.firstinspires.ftc.teamcode.commands.dump;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.BUCKET_CARRY;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_CARRY;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCarryCommand2 implements Command {
    final DumpSubsystem dumpSys;

    public DumpCarryCommand2(DumpSubsystem dump) {
        this.dumpSys = dump;
        addRequirements(dump);
    }

    @Override
    public void execute() {
        dumpSys.setServoPosition(BUCKET_CARRY);
        dumpSys.setMotorPosition(ARM_CARRY);
    }

    @Override
    public boolean isFinished() {
        return dumpSys.isMotorAtTarget() || getRuntime().seconds() > 1.0;
    }
}

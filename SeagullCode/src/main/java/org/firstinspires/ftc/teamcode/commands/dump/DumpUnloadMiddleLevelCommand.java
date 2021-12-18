package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_MIDDLE_LEVEL;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_TOP_LEVEL;

public class DumpUnloadMiddleLevelCommand implements Command {
    public DumpSubsystem dumpSys;

    public DumpUnloadMiddleLevelCommand(DumpSubsystem dumpSys){
        this.dumpSys = dumpSys;
        addRequirements(dumpSys);
    }

    @Override
    public void initialize() {
        dumpSys.setMotorPosition(ARM_TOP_LEVEL);
    }

    @Override
    public void execute() {
        if (getRuntime().seconds() > 0.5) {
            dumpSys.setMotorPosition(ARM_MIDDLE_LEVEL);
        }
    }

    @Override
    public boolean isFinished() {
        boolean finished = dumpSys.isMotorAtTarget() || getRuntime().seconds() > 1.5;
        if (finished){
            dumpSys.dumpBucket();
        }
        return finished;
    }
}

package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_MIDDLE_LEVEL;

public class DumpUnloadMiddleLevelCommand2 implements Command {
    public DumpSubsystem dumpSys;

    public DumpUnloadMiddleLevelCommand2(DumpSubsystem dumpSys){
        this.dumpSys = dumpSys;
        addRequirements(dumpSys);
    }

    @Override
    public void execute() {
        dumpSys.setMotorPosition(ARM_MIDDLE_LEVEL);
    }

    @Override
    public boolean isFinished() {
        boolean finished = dumpSys.isMotorAtTarget() || getRuntime().seconds() > 1.0;
        if (finished){
            dumpSys.dumpBucket();
        }
        return finished;
    }
}

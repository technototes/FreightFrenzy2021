package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_TOP_LEVEL;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.BucketConstant.BUCKET_TOP_LEVEL;

public class DumpUnloadTopLevelCommand2 implements Command {
    public DumpSubsystem dumpSys;

    public DumpUnloadTopLevelCommand2(DumpSubsystem dumpSys){
        this.dumpSys = dumpSys;
        addRequirements(dumpSys);
    }

    @Override
    public void execute() {
        dumpSys.setMotorPosition(ARM_TOP_LEVEL);
    }

    @Override
    public boolean isFinished() {
        boolean finished = dumpSys.isMotorAtTarget() || getRuntime().seconds() > 1.0;
        if (finished){
            dumpSys.setServoPosition(BUCKET_TOP_LEVEL);
        }
        return finished;
    }
}

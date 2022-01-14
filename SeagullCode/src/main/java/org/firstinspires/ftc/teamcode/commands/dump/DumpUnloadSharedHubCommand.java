package org.firstinspires.ftc.teamcode.commands.dump;

import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_MIDDLE_LEVEL;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_SHARED_HUB_LEVEL;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpUnloadSharedHubCommand implements Command {
    public DumpSubsystem dumpSys;

    public DumpUnloadSharedHubCommand(DumpSubsystem dumpSys){
        this.dumpSys = dumpSys;
        addRequirements(dumpSys);
    }

    @Override
    public void initialize() {
        dumpSys.setMotorPosition(ARM_SHARED_HUB_LEVEL);
    }

    @Override
    public void execute() {
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

package org.firstinspires.ftc.teamcode.commands.dump;

import com.technototes.library.command.Command;
import static org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem.ArmConstant.ARM_COLLECT;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class DumpCollectCommand2 implements Command {
    public DumpSubsystem dumpSys;

    public DumpCollectCommand2(DumpSubsystem dump) {
        this.dumpSys = dump;
        addRequirements(dump);
    }

    @Override
    public void execute() {
        dumpSys.setMotorPosition(ARM_COLLECT);
    }

    @Override
    public boolean isFinished() {
        return dumpSys.isMotorAtTarget() || getRuntime().seconds() > 1.0;
    }
}

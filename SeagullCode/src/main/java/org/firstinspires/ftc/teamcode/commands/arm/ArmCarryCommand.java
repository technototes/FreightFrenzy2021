package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class ArmCarryCommand extends ArmCommand {
    public ArmCarryCommand(DumpSubsystem arm) {
        super(arm, DumpSubsystem.ArmConstant.ARM_CARRY);
    }
}

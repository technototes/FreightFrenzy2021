package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.index.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;

import java.util.function.DoubleSupplier;

public class SendOneRingToShooterCommand extends SequentialCommandGroup {
    public SendOneRingToShooterCommand(IndexSubsystem subsystem){
        this(subsystem, ()->0);
    }
    public SendOneRingToShooterCommand(IndexSubsystem subsystem, DoubleSupplier d){
//        super(new ArmExtendCommand(subsystem),
//                new ParallelCommandGroup(new ArmRetractCommand(subsystem),
//                new IndexPivotMidCommand(subsystem)),
//                new IndexPivotUpCommand(subsystem),
//                new WaitCommand(d));
        super(new ArmExtendCommand(subsystem), new ArmRetractCommand(subsystem), new WaitCommand(d));
    }
    public SendOneRingToShooterCommand(IndexSubsystem subsystem, double d){
        this(subsystem, ()->d);
    }
}

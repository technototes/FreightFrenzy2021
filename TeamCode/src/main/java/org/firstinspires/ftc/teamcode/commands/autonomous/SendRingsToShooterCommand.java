package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;

import java.util.function.DoubleSupplier;

public class SendRingsToShooterCommand extends SequentialCommandGroup {
    public SendRingsToShooterCommand(IndexSubsystem subsystem, int num){
        super();
        while (num>0){
            addCommands(new SendOneRingToShooterCommand(subsystem));
            num--;
        }
    }
    public SendRingsToShooterCommand(IndexSubsystem subsystem, int num, double delay){
        super();
        while (num>0){
            addCommands(new SendOneRingToShooterCommand(subsystem, delay));
            num--;
        }
    }
    public SendRingsToShooterCommand(IndexSubsystem subsystem, int num, DoubleSupplier delay){
        super();
        while (num>0){
            addCommands(new SendOneRingToShooterCommand(subsystem, delay));
            num--;
        }
    }

}

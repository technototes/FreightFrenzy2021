package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.AlignToShootCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeedCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class PrepToShootCommand extends ParallelCommandGroup {
    public PrepToShootCommand(IndexSubsystem i, ShooterSubsystem s, DoubleSupplier speed, DoubleSupplier flap){
        super(
                new ShooterSetSpeedCommand(s, speed),
                new ShooterSetFlapCommand(s, flap),
                new ArmRetractCommand(i)
        );
    }
    public PrepToShootCommand(IndexSubsystem i, ShooterSubsystem s, double speed, double flap){
        super(
                new ShooterSetSpeedCommand(s, ()->speed),
                new ShooterSetFlapCommand(s, ()->flap),
                new ArmRetractCommand(i)
        );
    }
}

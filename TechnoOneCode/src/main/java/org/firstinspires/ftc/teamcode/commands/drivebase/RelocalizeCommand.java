package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class RelocalizeCommand implements Command {
    public DrivebaseSubsystem subsystem;
    public RelocalizeCommand(DrivebaseSubsystem s){
        subsystem = s;
    }
    @Override
    public void execute() {
        subsystem.relocalizePose(AutonomousConstants.ALLIANCE);
    }
}

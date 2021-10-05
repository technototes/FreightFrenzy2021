package org.firstinspires.ftc.teamcode.bot2.commands.drivebase;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;
//TODO this command
public class AlignToShootCommand implements Command {
    public DrivebaseSubsystem drivebaseSubsystem;
    public ShooterSubsystem shooterSubsystem;
    public AlignToShootCommand(DrivebaseSubsystem drive, ShooterSubsystem shoot){
        addRequirements(shoot);//, drive.dummySubsystem);
        drivebaseSubsystem = drive;
        shooterSubsystem = shoot;
    }

    @Override
    public void init() {
        //calculate changes needed
    }

    @Override
    public void execute() {
        shooterSubsystem.setVelocity(0.75);
    }

    @Override
    public boolean isFinished() {
        //check if changes are made
        return shooterSubsystem.getVelocity()>0.75;
    }
//    @Override
//    public void end(boolean cancel) {
//        drivebaseSubsystem.setDriveSignal(new DriveSignal());
//    }
}

package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.util.MathUtils;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.function.DoubleSupplier;

public class StraightenCommand implements Command {
    private static double DEADZONE = 2;
    public DrivebaseSubsystem subsystem;
    public DoubleSupplier r;
    public StraightenCommand(DrivebaseSubsystem sub) {
        addRequirements(sub);
        subsystem = sub;
    }
    @Override
    public void initialize() {
        /*
        double angle = Math.toDegrees(subsystem.getExternalHeading());
        int targetAngle = closestAngle(angle);
        subsystem.turnAsync(Math.toRadians(targetAngle - angle));
        */
        subsystem.turn(Math.toRadians(30));

    }
    @Override
    public void execute() {
        subsystem.update();
    }
    private int closestAngle(double angle) {
        double delta0 = Math.abs(angle);
        double delta90 = Math.abs(angle - 90);
        double delta180 = Math.abs(angle - 180);
        double delta270 = Math.abs(angle - 270);
        double delta360 = Math.abs(angle - 360);
        delta0 = Math.min(delta0, delta360);
        if (delta0 < delta90 && delta0 < delta180 && delta0 < delta270) {
            //delta0 is the smallest
            return 0;

        }
        else if (delta90 < delta180 && delta90 < delta270) {
            //delta90 is the smallest
            return 90;
        }
        else if (delta180 < delta270) {
            //delta180 is the smallest
            return 180;
        }
        else {
            //delta270 is the smallest
            return 270;
        }
    }
    @Override
    public boolean isFinished() {
        return false;
        /*
        double angle = Math.toDegrees(subsystem.getExternalHeading());
        int targetAngle = closestAngle(angle);
        return Math.abs(angle - targetAngle) < DEADZONE;
        */
    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.setDriveSignal(new DriveSignal());
        subsystem.turnAsync(0);
    }
}

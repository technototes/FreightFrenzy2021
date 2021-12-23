package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.Stick;
import com.technototes.library.util.MathUtils;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DriveCommand implements Command {
    static double STRAIGHTEN_DEAD_ZONE = 0.08;
    public DrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    public BooleanSupplier straight;
    public DriveCommand(DrivebaseSubsystem sub, Stick stick1, Stick stick2) {
        addRequirements(sub);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
        straight = null;
    }
    public DriveCommand(DrivebaseSubsystem sub, Stick stick1, Stick stick2, CommandButton straighten) {
        addRequirements(sub);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
        straight = straighten;
    }

    private double getRotation(double headingInRads) {
        // Check to see if we're trying to straighten the robot
        if (straight == null || straight.getAsBoolean() == false) {
            // No straighten override: return the stick value
            // (with some adjustment...)
            return -Math.pow(r.getAsDouble()*subsystem.speed, 3);
        } else {
            // headingInRads is [0-2pi]
            double heading = -Math.toDegrees(headingInRads);
            // Snap to the closest 90 or 270 degree angle (for going through the depot)
            double close = MathUtils.closestTo(heading, 90, 270 );
            double offBy = close - heading;
            // Normalize the error to -1 to 1
            double normalized = Math.max(Math.min(offBy / 45, 1.), -1.);
            // Dead zone of 5 degrees
            if (Math.abs(normalized) < STRAIGHTEN_DEAD_ZONE){
                return 0.0;
            }
            // Scale it by the cube root, the scale that down by 30%
            // .9 (about 40 degrees off) provides .96 power => .288
            // .1 (about 5 degrees off) provides .46 power => .14
            return Math.cbrt(normalized) * 0.3;
        }
    }
    @Override
    public void execute() {
        double curHeading = -subsystem.getExternalHeading();
        Vector2d input = new Vector2d(
                -y.getAsDouble()*subsystem.speed,
                -x.getAsDouble()*subsystem.speed
        ).rotated(curHeading);
        subsystem.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        getRotation(curHeading)
                )
        );
        subsystem.update();

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.setDriveSignal(new DriveSignal());
    }
}

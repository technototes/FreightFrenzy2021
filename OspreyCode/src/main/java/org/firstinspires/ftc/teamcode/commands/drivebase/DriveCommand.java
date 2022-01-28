package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.function.DoubleSupplier;
@Config
public class DriveCommand implements Command {

    public DrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    public DriveCommand(DrivebaseSubsystem sub, Stick stick1, Stick stick2) {
        addRequirements(sub);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
    }

    @Override
    public void execute() {
        Vector2d input = new Vector2d(
                y.getAsDouble(),
                x.getAsDouble()
        ).rotated(-subsystem.getExternalHeading()+ Math.toRadians(RobotConstants.getAlliance().selectOf(-90, 90)));
        subsystem.setSafeDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        getTurn(-Math.pow(r.getAsDouble()*subsystem.speed, 3)))
        );
    }
    //gotta tune all of this yay
    public static boolean ACCEL_TURNS = true, HEADING_LOCK = true;
    public static double ACCEL_LIMIT = 4, HEADING_P = 0.5, HEADING_TOLERANCE = 5;
    public ElapsedTime t = new ElapsedTime();
    public double past = 0, targetHeading = 0;
    public boolean targetingHeading = false;
    public double getTurn(double input){
        if(ACCEL_TURNS) {
            input = Range.clip(input, past-t.seconds()*ACCEL_LIMIT, past+t.seconds()*ACCEL_LIMIT);
            past = input;
            t.reset();
            if(Math.abs(input) > 0.02) targetingHeading = false;
        }
        if(HEADING_LOCK && Math.abs(input) < 0.02){
            double h = subsystem.getRawExternalHeading();
            if(!targetingHeading){
                targetHeading = h;
                targetingHeading = true;
            }
            if(Math.abs(Angle.normDelta(targetHeading-h)) > Math.toRadians(HEADING_TOLERANCE)) input=Angle.normDelta(targetHeading-h)*HEADING_P;
        }
        return input;
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
package org.firstinspires.ftc.teamcode.commands.drivebase;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Integral;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.Optional;
import java.util.function.DoubleSupplier;
@Config
public class DriveCommand implements Command {
    public static boolean HEADING_LOCK = true;
    public static double TARGET_HEADING_TOLERANCE = 5;
    public static double TURN_P = 1;
    public DrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    private double targetHeading;
    private boolean wasTurning;
    public DriveCommand(DrivebaseSubsystem sub, Stick stick1, Stick stick2) {
        addRequirements(sub);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
        wasTurning = false;
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
                        getTurn())
        );
    }

    public double getTurn(){
        if(Math.abs(r.getAsDouble())>0.1){
            wasTurning = true;
            return Math.pow(-r.getAsDouble(), 3);
        }
        if (wasTurning) {
            wasTurning = false;
            targetHeading = subsystem.getExternalHeading();
        }
        return Math.abs(Angle.normDelta(subsystem.getExternalHeading()-targetHeading)) < Math.toRadians(TARGET_HEADING_TOLERANCE) ? 0 : TURN_P*Angle.normDelta(subsystem.getExternalHeading()-targetHeading) ;


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
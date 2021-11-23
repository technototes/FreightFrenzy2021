package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.Command;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.function.Function;

@TeleOp
public class GenerativePathingTest extends CommandOpMode implements Loggable {
    public Robot robot;
    public Hardware hardware;


    @Override
    public void uponInit() {
        hardware = new Hardware();
        robot = new Robot(hardware);
    }
    public static class RegenerativeTrajectorySequenceCommand implements Command {
        DrivebaseSubsystem subsystem;
        TrajectorySequence t;
        public RegenerativeTrajectorySequenceCommand(DrivebaseSubsystem sub){
            subsystem = sub;
            addRequirements(sub);
        }


        @Override
        public void execute() {
            subsystem.update();
        }
    }
}

package org.firstinspires.ftc.teamcode.commands.autonomous;



import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;


public class AutoIntakeCommand extends SequentialCommandGroup {
    public AutoIntakeCommand(DrivebaseSubsystem drive, IntakeSubsystem intake){
        super(new IntakeInCommand(intake), // Intake command - spin the intake before arrived at the depot
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT),
                new WaitCommand(1), //Intake command
                new IntakeStopCommand(intake)); //Intake command)

    }

}

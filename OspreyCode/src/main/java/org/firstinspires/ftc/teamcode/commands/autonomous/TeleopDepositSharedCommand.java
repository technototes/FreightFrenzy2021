package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.RegenerativeTrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DepositSharedCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveRelocalizeCycleCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveRelocalizeSharedCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class TeleopDepositSharedCommand extends SequentialCommandGroup {
    public DrivebaseSubsystem drivebaseSubsystem;
    public TeleopDepositSharedCommand(DrivebaseSubsystem drive, IntakeSubsystem intake, LiftSubsystem lift, ArmSubsystem deposit, ExtensionSubsystem extension) {
                super(new DriveRelocalizeSharedCommand(drive),
                new RegenerativeTrajectorySequenceCommand(drive, RobotConstants.WAREHOUSE_TO_SHARED_HUB, drive)
                        .alongWith(new IntakeOutCommand(intake).withTimeout(0.5),
                                //new WaitCommand(0.3).andThen(new DriveRelocalizeSharedCommand(drive)),
                                new WaitCommand(0.1).andThen(new DepositSharedCommand(deposit, extension, lift))),
                new WaitCommand(0.1),
                new BucketDumpCommand(deposit));
        drivebaseSubsystem = drive;
    }

    @Override
    public void initialize() {
        super.initialize();
        RobotConstants.startDeposit();
        drivebaseSubsystem.setExternalHeading(drivebaseSubsystem.getExternalHeading()+Math.toRadians(RobotConstants.getAlliance().selectOf(-90, 90)));

    }

    @Override
    public void end(boolean cancel) {
        super.end(cancel);
        drivebaseSubsystem.setExternalHeading(drivebaseSubsystem.getExternalHeading()+ Math.toRadians(RobotConstants.getAlliance().selectOf(90, -90)));
    }
}

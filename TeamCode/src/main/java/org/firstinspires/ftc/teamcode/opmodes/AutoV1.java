package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Pair;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Log;
import com.technototes.logger.LogConfig;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.AlignToShootCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.index.IndexPivotDownCommand;
import org.firstinspires.ftc.teamcode.commands.index.IndexPivotUpCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Main OpMode
 *
 */
@TeleOp(name = "AutoV1-modified")
public class AutoV1 extends CommandOpMode implements Loggable {
    /** The robot
     *
     */
    @LogConfig.Disabled
    public Robot robot;

    //for drive its xyr
    //for sp
    public static List<Pair<String, double[]>> pointCustomizations;
    @Log(index = 0, name = "Current Choice")
    public static String string(){
        return getChoice().first;
    }
    public static Pair<String, double[]> getChoice(){
        return pointCustomizations.get(currentChoice);
    }
    public static int currentChoice;

    @Log(index = 1, name = "Values")
    public static String getCurrentChoice(){
        String r = "";
        int i = 0;
        for(double d : getChoice().second) {
            r += i +": "+d+", ";
            i++;
        }
        return r;
    }

    @Override
    public void uponInit() {
        robot = new Robot();
        if(pointCustomizations == null){
            pointCustomizations = new ArrayList<>();
            pointCustomizations.add(new Pair<>("Initial Shooting, 1 is flap (*100), 2 is rotation", new double[]{40, -10}));
            pointCustomizations.add(new Pair<>("Secondary Shooting, 1 is flap (*100), 2 is rotation", new double[]{50, -15}));
            pointCustomizations.add(new Pair<>("secondary shooting position, xy and end tan accordingly", new double[]{48, 12, 0}));
            pointCustomizations.add(new Pair<>("Shooter delay between shots, 1 is time(*100)", new double[]{20}));
            pointCustomizations.add(new Pair<>("first wobble drop position, xy and rotation accordingly", new double[]{120, 1, 90}));
            pointCustomizations.add(new Pair<>("second wobble grab position, xy and rotation accordingly", new double[]{30, 29, -30}));
            pointCustomizations.add(new Pair<>("second wobble drop position, xy and rotation accordingly", new double[]{115, 1, 90}));
            pointCustomizations.add(new Pair<>("end position, xy and rotation accordingly", new double[]{80, 10, 82}));

        }
    }

    @Override
    public void uponStart() {
        driverGamepad.x.whenPressed(new SequentialCommandGroup(
                //prep to shoot
                new ParallelCommandGroup(
                        new TurnCommand(robot.drivebaseSubsystem, pointCustomizations.get(0).second[1]),//-10
                        new InstantCommand(()->robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED)),
                        new IndexPivotUpCommand(robot.indexSubsystem),
                        new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                        new ShooterSetFlapCommand(robot.shooterSubsystem, ()->pointCustomizations.get(0).second[0]/100.0),//0.4
                        new WaitCommand(0.3)),
                //shoot 3 rings
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                new WaitCommand(pointCustomizations.get(3).second[0]/100.0), //0.2
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
//                new WaitCommand(0.1),
//                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                //move to stack
                new ParallelCommandGroup(
                        new IndexPivotDownCommand(robot.indexSubsystem),
                        new ShooterStopCommand(robot.shooterSubsystem),
                        new IntakeInCommand(robot.intakeSubsystem),
                        new SplineCommand(robot.drivebaseSubsystem, pointCustomizations.get(2).second[0], pointCustomizations.get(2).second[1], pointCustomizations.get(2).second[2], pointCustomizations.get(1).second[1])), //48 12 0 -15
                //prep to shoot
                new ParallelCommandGroup(
                        new IndexPivotUpCommand(robot.indexSubsystem),
                        new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                        new IntakeStopCommand(robot.intakeSubsystem),
                        new ShooterSetFlapCommand(robot.shooterSubsystem, ()->pointCustomizations.get(2).second[0]/100.0)//0.3
                        ),
                //shoot 3 rings
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                new WaitCommand(pointCustomizations.get(3).second[0]/100.0), //0.2
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                new WaitCommand(pointCustomizations.get(3).second[0]/100.0), //0.2
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                //move to drop point
                new ParallelCommandGroup(
                        new IndexPivotDownCommand(robot.indexSubsystem),
                        new ShooterStopCommand(robot.shooterSubsystem),
                        new StrafeCommand(robot.drivebaseSubsystem, pointCustomizations.get(4).second[0], pointCustomizations.get(4).second[1],  pointCustomizations.get(4).second[2]),// 120 1 90
                        new WobbleLowerCommand(robot.wobbleSubsystem)),
                //drop 1st wobble
                //new TurnCommand(robot.drivebaseSubsystem, 90),
                new WobbleOpenCommand(robot.wobbleSubsystem),
                //move to 2nd wobble
                new StrafeCommand(robot.drivebaseSubsystem, pointCustomizations.get(5).second[0], pointCustomizations.get(5).second[1], pointCustomizations.get(5).second[2]),//30 29 -30
                //grab 2nd wobble
                new WobbleCloseCommand(robot.wobbleSubsystem),
                //move to drop point
                new StrafeCommand(robot.drivebaseSubsystem, pointCustomizations.get(6).second[0], pointCustomizations.get(6).second[1], pointCustomizations.get(6).second[2]), //115 1 90
                //drop 2nd wobble
                new WobbleOpenCommand(robot.wobbleSubsystem),
                //goto line
                new ParallelCommandGroup(
                        new StrafeCommand(robot.drivebaseSubsystem, pointCustomizations.get(7).second[0], pointCustomizations.get(7).second[1], pointCustomizations.get(7).second[2]), //80 10 82
                        new SequentialCommandGroup(new WobbleRaiseCommand(robot.wobbleSubsystem), new WobbleCloseCommand(robot.wobbleSubsystem))),
                new InstantCommand(()->robot.drivebaseSubsystem.setPoseEstimate(new Pose2d())),
                new InstantCommand(this::terminate)
        ));
    }
}

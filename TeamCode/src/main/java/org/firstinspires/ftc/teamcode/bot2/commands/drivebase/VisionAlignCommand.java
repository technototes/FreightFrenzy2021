package org.firstinspires.ftc.teamcode.bot2.commands.drivebase;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.teamcode.bot2.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.VisionAimSubsystem;

public class VisionAlignCommand extends WaitCommand {
    public  VisionAimSubsystem visionSubsystem;
    public TurretSubsystem turretSubsystem;
    public int target = 24;
    public static final double P = 0.0002;

    public VisionAlignCommand(TurretSubsystem t, VisionAimSubsystem v){
        this(t, v, 0);
    }

    public VisionAlignCommand(TurretSubsystem t, VisionAimSubsystem v, double time){
        super(time);
        turretSubsystem = t;
        visionSubsystem = v;
    }

    double cur;

    @Override
    public void execute() {
        cur = visionSubsystem.getAvg();
        turretSubsystem.changeBy(Math.pow(Range.clip(-(cur-target), -5, 5), 3)*P);
    }

}

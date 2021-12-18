package org.firstinspires.ftc.teamcode.commands.arm;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class ArmBarcodeSelectCommand extends ArmCommand{
    public VisionSubsystem visionSubsystem;
    public ArmBarcodeSelectCommand(ArmSubsystem s, VisionSubsystem v){
        super(s);
        visionSubsystem = v;
    }

    @Override
    public void execute() {
        subsystem.carry();
        if(visionSubsystem.barcodePipeline.zero()) subsystem.down();
        else subsystem.out();
    }
}


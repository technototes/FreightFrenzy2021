package org.firstinspires.ftc.teamcode.commands.extension;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class ExtensionBarcodeSelectCommand extends ExtensionOutCommand {
    public VisionSubsystem visionSubsystem;
    public ExtensionBarcodeSelectCommand(ExtensionSubsystem s, VisionSubsystem v){
        super(s);
        visionSubsystem = v;
    }

    @Override
    public void execute() {
        extensionSubsystem.center();
        extensionSubsystem.setSlide(visionSubsystem.barcodePipeline.zero() ? ExtensionSubsystem.ExtensionConstants.LOW_GOAL_AUTO : ExtensionSubsystem.ExtensionConstants.OUT);
    }
}


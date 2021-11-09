package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Color;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.firstinspires.ftc.teamcode.commands.vision.VisionBarcodeCommand;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

import static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionConstants.*;

// Credits to team 7303 RoboAvatars, adjusted by team 3954 Pink to the Future

public class VisionSubsystem extends PipelineSubsystem implements Loggable {
    @Config
    public static class VisionConstants{
        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

    }

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Number(name = "Barcode", color = Color.GREEN)
    public BarcodePipeline barcodePipeline = new BarcodePipeline();

    public VisionSubsystem(Camera c) {
        super(c);
        CommandScheduler.getInstance().scheduleForState(new VisionBarcodeCommand(this), CommandOpMode.OpModeState.INIT);
        c.openCameraDeviceAsync(this::startStreaming, System.out::println);

    }

    public VisionSubsystem startStreaming(){
        return (VisionSubsystem) startStreaming(WIDTH, HEIGHT, ROTATION);
    }

    public VisionSubsystem startBarcodePipeline(){
        setActivePipeline(barcodePipeline);
        return this;
    }
    public VisionSubsystem stopBarcodePipeline(){
        camera.closeCameraDeviceAsync(camera::stopStreaming);
        return this;
    }
}
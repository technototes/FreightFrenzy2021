package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.util.Color;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.openftc.easyopencv.OpenCvCameraRotation;

import static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionConstants.*;

// Credits to team 7303 RoboAvatars, adjusted by team 3954 Pink to the Future

public class VisionSubsystem extends PipelineSubsystem implements Loggable {
    @Config
    public static class VisionConstants implements Loggable {
        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;
        @LogConfig.Run(duringRun = false, duringInit = true)
        @Log.Number(name = "Barcode", color = Color.GREEN)
        public static BarcodePipeline BARCODE_PIPELINE = new BarcodePipeline();
    }

    public VisionSubsystem(Camera c) {
        super(c);
    }

    public PipelineSubsystem startStreaming(){
        return startStreaming(WIDTH, HEIGHT, ROTATION);
    }

}
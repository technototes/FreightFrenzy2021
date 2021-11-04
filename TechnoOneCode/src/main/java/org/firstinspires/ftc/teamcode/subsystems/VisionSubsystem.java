package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionConstants.*;

// Credits to team 7303 RoboAvatars, adjusted by team 3954 Pink to the Future

public class VisionSubsystem extends PipelineSubsystem implements Supplier<Integer> {
    @Config
    public static class VisionConstants {
        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;
        public static BarcodePipeline BARCODE_PIPELINE = new BarcodePipeline();
    }

    public VisionSubsystem(Camera c) {
        super(c);
    }

    public PipelineSubsystem startStreaming(){
        return startStreaming(WIDTH, HEIGHT, ROTATION);
    }
    @Override
    public Integer get() {
        return null;
    }
}
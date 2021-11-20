package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Color;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import static com.technototes.library.hardware.HardwareDevice.hardwareMap;
import static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionConstants.HEIGHT;
import static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionConstants.ROTATION;
import static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionConstants.WIDTH;

// Credits to team 7303 RoboAvatars, adjusted by team 3954 Pink to the Future

public class VisionSubsystem implements Subsystem, Loggable {
    @Config
    public static class VisionConstants {
        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

    }

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Number(name = "Barcode", color = Color.GREEN)
    public BarcodePipeline barcodePipeline = new BarcodePipeline();

    public Webcam camera;

    public VisionSubsystem(Webcam c) {
        camera = c;
    }

    public void startStreaming() {
        camera.startStreaming(WIDTH, HEIGHT, ROTATION);
    }

    public void startBarcodePipeline() {
        camera.setPipeline(barcodePipeline);
        camera.openCameraDeviceAsync(this::startStreaming, i->startBarcodePipeline());
    }

    public void stopBarcodePipeline() {
        camera.setPipeline(null);
        camera.closeCameraDeviceAsync(()->{});
    }

}
package com.technototes.vision.subsystem;

import com.technototes.library.command.Command;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.vision.hardware.Camera;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class VisionSubsystem extends OpenCvPipeline implements Subsystem<Camera> {

    protected Camera camera;

    public VisionSubsystem(Camera c){
        camera = c;
    }

    @Override
    public Camera getDevice() {
        return camera;
    }

    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}

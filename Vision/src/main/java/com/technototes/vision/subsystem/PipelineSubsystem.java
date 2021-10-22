package com.technototes.vision.subsystem;

import com.technototes.library.subsystem.Subsystem;
import com.technototes.vision.hardware.Camera;

import org.openftc.easyopencv.OpenCvPipeline;

public abstract class PipelineSubsystem extends OpenCvPipeline implements Subsystem<Camera> {

    protected Camera camera;

    public PipelineSubsystem(Camera c){
        camera = c;
    }

    @Override
    public Camera getDevice() {
        return camera;
    }

}

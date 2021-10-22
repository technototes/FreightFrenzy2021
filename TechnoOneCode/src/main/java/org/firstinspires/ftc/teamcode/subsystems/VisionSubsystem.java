package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.opencv.core.Mat;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class VisionSubsystem extends PipelineSubsystem implements Supplier<Integer> {

    public VisionSubsystem(Camera c) {
        super(c);
    }

    @Override
    public Mat processFrame(Mat input) {
        return null;
    }

    @Override
    public Integer get() {
        return 0;
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.opencv.core.Mat;

import java.util.function.Supplier;

public class VisionSubsystem extends PipelineSubsystem implements Supplier<Integer> {
  @Config
  public static class VisionSubsystemConstants{
    public static double RANGE_CORRECTION;
    public static double LEFT_RANGE, MIDDLE_RANGE, RIGHT_RANGE;
  }

  public VisionSubsystem(Camera c) {
    super(c);
  }

  public double get_green_position() {
    return 0.0;
  }

  @Override
  public Integer get() {
    return null;
  }

}

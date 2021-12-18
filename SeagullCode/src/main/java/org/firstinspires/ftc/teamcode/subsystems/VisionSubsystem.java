package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.hardware.Webcam;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.function.Supplier;
import  static org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem.VisionSubsystemConstants;


public class VisionSubsystem implements Subsystem, Loggable {
  @Config
  public static class VisionSubsystemConstants{
    public static int WIDTH = 320;
    public static int HEIGHT = 240;
    public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;
  }
  public BarcodePipeline barcodePipeline = new BarcodePipeline();
  public Webcam camera;

  public VisionSubsystem(Webcam c) {

    camera = c;
  }
  public void startStreaming(){
    camera.startStreaming(VisionSubsystemConstants.WIDTH, VisionSubsystemConstants.HEIGHT, VisionSubsystemConstants.ROTATION);

  }
  public void startBarcodePipeline(){
    camera.setPipeline(barcodePipeline);
    camera.openCameraDeviceAsync(this::startStreaming, i -> startBarcodePipeline());
  }
  public void stopBarcodePipeline(){
    camera.setPipeline(null);
    camera.closeCameraDeviceAsync(()->{});
  }

  public double get_green_position() {
    return 0.0;
  }



}

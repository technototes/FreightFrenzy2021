package com.technototes.vision.hardware;


import com.technototes.library.hardware.HardwareDevice;
import com.technototes.library.hardware.HardwareDeviceGroup;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvSwitchableWebcam;

import java.util.Arrays;

public class SwitchableWebcam extends Camera<OpenCvSwitchableWebcam, WebcamName[]> implements HardwareDeviceGroup {

    private Webcam[] devices;

    public SwitchableWebcam(WebcamName... device) {
        super(device);
        devices = Arrays.stream(device).map(Webcam::new).toArray(Webcam[]::new);
    }
    public SwitchableWebcam(String... device) {
        this(Arrays.stream(device).map((s)->hardwareMap.get(WebcamName.class, s)).toArray(WebcamName[]::new));
    }

    public SwitchableWebcam(Webcam... device) {
        this(Arrays.stream(device).map(HardwareDevice::getDevice).toArray(WebcamName[]::new));
    }

    @Override
    public OpenCvSwitchableWebcam createCamera() {
        return OpenCvCameraFactory.getInstance().createSwitchableWebcam(
                hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                        hardwareMap.appContext.getPackageName()), getDevice());
    }

    public SwitchableWebcam setActiveCamera(Webcam w){
        return setActiveCamera(w.getDevice());
    }

    public SwitchableWebcam setActiveCamera(WebcamName device) {
        openCvCamera.setActiveCamera(device);
        return this;
    }

    public SwitchableWebcam setActiveCamera(String device) {
        return setActiveCamera(hardwareMap.get(WebcamName.class, device));
    }


    public Webcam getActiveCamera(){
        return Arrays.stream(devices).filter((s)-> s.getDevice() == openCvCamera.getActiveCamera()).findFirst().get();
    }


    @Override
    public Webcam[] getFollowers() {
        Webcam w2 = getActiveCamera();
        return Arrays.stream(getAllDevices()).filter((w)->w!=w2).toArray(Webcam[]::new);
    }

    @Override
    public Webcam[] getAllDevices() {
        return devices;
    }

}

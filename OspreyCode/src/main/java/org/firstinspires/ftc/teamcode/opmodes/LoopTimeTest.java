package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="looptimetest")
public class LoopTimeTest extends CommandOpMode {
    public Rev2MDistanceSensor sensor;

    @Log.Number(index = 0, name = "sensor calls per loop")
    public double sensorLoops = 0;

    @Log.Number(index = 1, name = "looptime in hz")
    public double hz;

    @Log.Number(index = 2, name = "avg sensor call time")
    public double timePerSensorCall;

    @Log.Number(index = 3, name = "sensor distance inches")
    public double distance;


    private long pastTime;

    @Override
    public void uponInit() {
        sensor = new Rev2MDistanceSensor("fdistance").setDistanceUnit(DistanceUnit.INCH);

        driverGamepad.dpadUp.whenPressed(()->sensorLoops++);
        driverGamepad.dpadDown.whenPressed(()->sensorLoops--);

    }

    @Override
    public void runLoop() {
        long l = System.currentTimeMillis();
        for(int i = 0; i < sensorLoops; i++){
            distance = sensor.getSensorValue();
        }
        if(sensorLoops!=0) timePerSensorCall = (System.currentTimeMillis()-l)/sensorLoops;
        hz = 1000.0/(System.currentTimeMillis()-pastTime);
        pastTime = System.currentTimeMillis();
    }
}

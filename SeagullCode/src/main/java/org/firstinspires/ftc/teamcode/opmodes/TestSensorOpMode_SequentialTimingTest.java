package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.subsystems.SensorSubsystem;

@TeleOp(name = "TestSensor_SequentialTimingTest")
@SuppressWarnings("unused")
public class TestSensorOpMode_SequentialTimingTest extends CommandOpMode implements Loggable {
    public SensorSubsystem sensorSubsystem;
    private Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware();
        sensorSubsystem = new SensorSubsystem(hardware.frontRangeSensor,
                hardware.leftRangeSensor,
                hardware.rightRangeSensor,
                hardware.bucketRangeSensor,
                SensorSubsystem.ReadType.SEQUENTIAL_TEST_TIMING);

        sensorSubsystem.setDefaultCommand(new SensorSubsystem.TestCommand(sensorSubsystem));
    }

    @Override
    public void end(){
        sensorSubsystem.destroy();
    }
}

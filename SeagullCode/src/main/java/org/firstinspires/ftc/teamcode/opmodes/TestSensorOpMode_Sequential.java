package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.SensorSubsystem;

@TeleOp(name = "TestSensor_Sequential")
@SuppressWarnings("unused")
public class TestSensorOpMode_Sequential extends CommandOpMode implements Loggable {
    public SensorSubsystem sensorSubsystem;
    private Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware();
        sensorSubsystem = new SensorSubsystem(hardware.frontRangeSensor,
                hardware.leftRangeSensor,
                hardware.rightRangeSensor,
                hardware.bucketRangeSensor,
                SensorSubsystem.ReadType.SEQUENTIAL_INLINE);

        sensorSubsystem.setDefaultCommand(new SensorSubsystem.TestCommand(sensorSubsystem));
    }

    @Override
    public void end(){
        sensorSubsystem.destroy();
    }
}

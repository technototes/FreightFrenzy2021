package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;
import static org.firstinspires.ftc.teamcode.Hardware.HardwareConstants.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.IMU.AxesSigns;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

public class Hardware {
    @Config
    public static class HardwareConstants {
        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";

        public static String FRONT_RANGE = "front_range";
    }

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;

    public Hardware() {
        if (DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, AxesSigns.NNN);
        }
    }
}
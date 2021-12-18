package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;

@Config
public class LiftConstants {
    public static double LIFT_UPPER_LIMIT = 400.0;
    public static double LIFT_LOWER_LIMIT = 0.0;
    //300 for single slide
    public static double COLLECT = 0, NEUTRAL = 100, LEVEL_1 = 50, LEVEL_2 = 200, LEVEL_3 = 400;

    public static double DEADZONE = 30;

    public static PIDCoefficients PID = new PIDCoefficients(0.02, 0, 0.001);

}

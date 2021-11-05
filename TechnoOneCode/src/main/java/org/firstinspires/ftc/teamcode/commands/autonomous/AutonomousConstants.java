package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.util.Alliance;

import static java.lang.Math.toRadians;

@Config
public class AutonomousConstants {
    @Config
    public static class RedConstants {
        public static Pose2d START = new Pose2d(0, -65, toRadians(90));
        public static Pose2d DEPOSIT = new Pose2d(0, -40, toRadians(120));
        public static Pose2d GAP = new Pose2d(32, -64.5, toRadians(0));
        public static Pose2d COLLECT = new Pose2d(44, -64.5, toRadians(0));

    }
    @Config
    public static class BlueConstants {
        public static Pose2d START = new Pose2d(0, 65, toRadians(-90));
        public static Pose2d DEPOSIT = new Pose2d(0, 40, toRadians(-120));
        public static Pose2d GAP = new Pose2d(32, 64.5, toRadians(180));
        public static Pose2d COLLECT = new Pose2d(44, 64.5, toRadians(180));

    }

    public static Alliance ALLIANCE = Alliance.RED;

    public static final Alliance.Selector<Pose2d>
            START_SELECT = Alliance.Selector.of(RedConstants.START, BlueConstants.START),
            DEPOSIT_SELECT = Alliance.Selector.of(RedConstants.DEPOSIT, BlueConstants.DEPOSIT),
            GAP_SELECT = Alliance.Selector.of(RedConstants.GAP, BlueConstants.GAP),
            COLLECT_SELECT = Alliance.Selector.of(RedConstants.COLLECT, BlueConstants.COLLECT);



}


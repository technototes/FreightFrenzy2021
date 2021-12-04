package org.firstinspires.ftc.teamcode.commands.autonomous;

import static java.lang.Math.toRadians;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.util.Alliance;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

@Config
public class AutonomousConstants {
    public static class RedConstants {
        public static Pose2d DUCK_START = new Pose2d(-36, -63, toRadians(-90));
        public static Pose2d DUCK_HUB = new Pose2d(-30, -38, toRadians(-125));
        public static Pose2d CAROUSEL = new Pose2d(-66, -58, toRadians(-90));
        public static Pose2d DUCK_PARK = new Pose2d(-67, -31, toRadians(0)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d DEPOT_START = new Pose2d(0, -66, toRadians(-90)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB = new Pose2d(-10, -39, toRadians(-68)); // Wrong positions (estimate)
        public static Pose2d DEPOT_PARK = new Pose2d(36, -65.5, toRadians(0)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d DEPOT_GAP = new Pose2d(8, -68, toRadians(0));
    }

    public static class BlueConstants {
        public static Pose2d DUCK_START = new Pose2d(-36, 63, toRadians(90));
        public static Pose2d CAROUSEL = new Pose2d(-59, 59, toRadians(180));
        public static Pose2d DUCK_PARK = new Pose2d(-62, 30, toRadians(0)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d SHIPPING_HUB_START = new Pose2d(0, 66, toRadians(90)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB = new Pose2d(-9, 41, toRadians(60)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB_TO_DEPOT = new Pose2d(0, 69.5, toRadians(0)); // Wrong positions (estimate)
        public static Pose2d DEPOT_PARK = new Pose2d(36, 69.5, toRadians(0)); // Not wrong positions (everything is fine, DO NOT CHANGE)
    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    private static int cycles = 0;

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
              // Lists of driving series for auto naviation
              RED_DUCK_START_TO_CAROUSEL = b -> b.apply(RedConstants.DUCK_START)
              .lineToLinearHeading(RedConstants.CAROUSEL)
              .build(),
              RED_DUCK_START_TO_HUB = b -> b.apply(RedConstants.DUCK_START)
                        .lineToLinearHeading(RedConstants.DUCK_HUB)
                        .build(),
              RED_DUCK_HUB_TO_CAROUSEL = b -> b.apply(RedConstants.DUCK_HUB)
                        .lineToLinearHeading(RedConstants.CAROUSEL)
                        .build(),
              RED_DUCK_CAROUSEL_TO_PARK = b -> b.apply(RedConstants.CAROUSEL)
                        .lineToLinearHeading(RedConstants.DUCK_PARK)
                        .build(),
              BLUE_DUCK_START_TO_CAROUSEL = b -> b.apply(BlueConstants.DUCK_START)
                        .lineToLinearHeading(BlueConstants.CAROUSEL)
                        .build(),
              BLUE_DUCK_CAROUSEL_TO_PARK = b -> b.apply(BlueConstants.CAROUSEL)
                        .lineToLinearHeading(BlueConstants.DUCK_PARK)
                        .build(),
              RED_START_TO_SHIPPING_HUB = b -> b.apply(RedConstants.DEPOT_START)
                        .lineToLinearHeading(RedConstants.SHIPPING_HUB)
                        .build(),
              RED_SHIPPING_HUB_TO_DEPOT = b -> b.apply(RedConstants.SHIPPING_HUB)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.DEPOT_PARK)
                        .build(),
              RED_DEPOT_TO_SHIPPING_HUB = b -> b.apply(RedConstants.DEPOT_PARK)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.SHIPPING_HUB)
                        .build(),
              BLUE_START_TO_SHIPPING_HUB = b -> b.apply(BlueConstants.SHIPPING_HUB_START)
                        .lineToLinearHeading(BlueConstants.SHIPPING_HUB)
                        .build(),
              BLUE_SHIPPING_HUB_TO_DEPOT = b -> b.apply(BlueConstants.SHIPPING_HUB)
                        .lineToLinearHeading(BlueConstants.SHIPPING_HUB_TO_DEPOT)
                        .lineToLinearHeading(BlueConstants.DEPOT_PARK)
                        .build();
}


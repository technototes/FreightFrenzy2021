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
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL3 = new Pose2d(-30, -38, toRadians(-125));
        public static Pose2d DUCK_CAROUSEL = new Pose2d(-66, -58, toRadians(-90));
        public static Pose2d DUCK_PARK = new Pose2d(-67, -31, toRadians(180)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d DEPOT_START = new Pose2d(0, -66, toRadians(-90)); // Wrong positions (estimate)
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL3 = new Pose2d(-10, -45, toRadians(-68)); // Wrong positions (estimate)
        public static Pose2d DEPOT_GAP = new Pose2d(8, -66, toRadians(0));
        public static Pose2d DEPOT_COLLECT = new Pose2d(46.5, -66, toRadians(0)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d DEPOT_PARK = new Pose2d(46.5, -66, toRadians(180));
    }

    public static class BlueConstants {
        public static Pose2d DUCK_START = new Pose2d(-36, 63, toRadians(90));
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL3 = new Pose2d(-23, 38, toRadians(120));
        public static Pose2d DUCK_CAROUSEL = new Pose2d(-59, 59, toRadians(180));
        public static Pose2d DUCK_PARK = new Pose2d(-62, 30, toRadians(180)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d DEPOT_START = new Pose2d(0, 66, toRadians(90)); // Wrong positions (estimate)
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL3 = new Pose2d(-9, 41, toRadians(60)); // Wrong positions (estimate)
        public static Pose2d DEPOT_GAP = new Pose2d(0, 69.5, toRadians(0)); // Wrong positions (estimate)
        public static Pose2d DEPOT_COLLECT = new Pose2d(36, 69.5, toRadians(0)); // Not wrong positions (everything is fine, DO NOT CHANGE)
        public static Pose2d DEPOT_PARK = new Pose2d(36, 69.5, toRadians(180));
    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    private static int cycles = 0;

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
              // Lists of driving series for auto naviation
              RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DUCK_START)
                        .lineToLinearHeading(RedConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .build(),
              RED_DUCK_ALLIANCE_HUB_TO_CAROUSEL = b -> b.apply(RedConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(RedConstants.DUCK_CAROUSEL)
                        .build(),
              RED_DUCK_CAROUSEL_TO_PARK = b -> b.apply(RedConstants.DUCK_CAROUSEL)
                        .lineToLinearHeading(RedConstants.DUCK_PARK)
                        .build(),
              RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DEPOT_START)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .build(),
              RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e)->25)
                        .lineTo(RedConstants.DEPOT_COLLECT.vec())
                        .build(),
              RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT_PARK = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e)->25)
                        .lineTo(RedConstants.DEPOT_PARK.vec())
                        .build(),
              RED_DEPOT_COLLECT_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DEPOT_COLLECT)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .build(),
              BLUE_DUCK_CAROUSEL_TO_PARK = b -> b.apply(BlueConstants.DUCK_CAROUSEL)
                        .lineToLinearHeading(BlueConstants.DUCK_PARK)
                        .build(),
              BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_3 = b -> b.apply(BlueConstants.DUCK_START)
                      .lineToLinearHeading(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                      .build(),
              BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(BlueConstants.DEPOT_START)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(BlueConstants.DUCK_CAROUSEL)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e)->20)
                        .lineToLinearHeading(BlueConstants.DEPOT_COLLECT)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL3_TO_DEPOT_PARK = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e)->20)
                        .lineToLinearHeading(BlueConstants.DEPOT_PARK)
                        .build(),
              BLUE_DEPOT_COLLECT_TO_ALLIANCE_HUB_LEVEL_3 = b -> b.apply(BlueConstants.DEPOT_COLLECT)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .build();
}


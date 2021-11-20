package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.util.Alliance;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Math.toRadians;

@Config
public class AutonomousConstants {
    public static class RedConstants {
        public static Pose2d DUCK_START = new Pose2d(-36, -63, toRadians(-90));
        public static Pose2d CAROUSEL = new Pose2d(-68, -58, toRadians(-90));
        public static Pose2d PARK = new Pose2d(-65, -33, toRadians(0));
        public static Pose2d SHIPPING_HUB_START = new Pose2d(0, -66, toRadians(0)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB = new Pose2d(-10,-42, toRadians(33)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB_TO_DEPOT = new Pose2d(-0, -66, toRadians(-90)); // Wrong positions (estimate)
        public static Pose2d DEPOT_PARK = new Pose2d(55, -66, toRadians(-90)); // Wrong positions (estimate)
    }

    public static class BlueConstants {
        public static Pose2d DUCK_START = new Pose2d(-24, 63, toRadians(90));
        public static Pose2d CAROUSEL = new Pose2d(-60, 60, toRadians(180));
        public static Pose2d PARK = new Pose2d(-62, 36, toRadians(0));
        public static Pose2d SHIPPING_HUB_START = new Pose2d(0, 66, toRadians(180)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB = new Pose2d(-10,42, toRadians(-147)); // Wrong positions (estimate)
        public static Pose2d SHIPPING_HUB_TO_DEPOT = new Pose2d(-0, 66, toRadians(90)); // Wrong positions (estimate)
        public static Pose2d DEPOT_PARK = new Pose2d(55, 66, toRadians(90)); // Wrong positions (estimate)
    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    public static final Supplier<Pose2d>
            DUCK_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_START, BlueConstants.DUCK_START),
            CAROUSEL_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CAROUSEL, BlueConstants.CAROUSEL),
            DUCK_PARK_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.PARK, BlueConstants.PARK),
            SHIPPING_HUB_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.SHIPPING_HUB_START, BlueConstants.SHIPPING_HUB_START),
            SHIPPING_HUB_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.SHIPPING_HUB, BlueConstants.SHIPPING_HUB),
            SHIPPING_HUB_TO_DEPOT_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.SHIPPING_HUB_TO_DEPOT, BlueConstants.SHIPPING_HUB_TO_DEPOT),
            DEPOT_PARK_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DEPOT_PARK, BlueConstants.DEPOT_PARK);

    private static int cycles = 0;

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

            DUCK_START_TO_CAROUSEL = b -> b.apply(DUCK_START_SELECT.get())
                    .lineToLinearHeading(CAROUSEL_SELECT.get())
                    .build(),
            DUCK_CAROUSEL_TO_PARK = b -> b.apply(CAROUSEL_SELECT.get())
                    .lineToLinearHeading(DUCK_PARK_SELECT.get())
                    .build(),
            START_TO_SHIPPING_HUB = b -> b.apply(SHIPPING_HUB_START_SELECT.get())
                    .lineToLinearHeading(SHIPPING_HUB_SELECT.get())
                    .build(),
            SHIPPING_HUB_TO_DEPOT = b -> b.apply(SHIPPING_HUB_SELECT.get())
                    .lineToLinearHeading(SHIPPING_HUB_TO_DEPOT_SELECT.get())
                    .lineToLinearHeading(DEPOT_PARK_SELECT.get())
                    .build();

}


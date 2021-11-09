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
        public static Pose2d DUCK_START = new Pose2d(-24, -63, toRadians(90));
        public static Pose2d CAROUSEL = new Pose2d(-60, -60, toRadians(0));
        public static Pose2d PARK = new Pose2d(-62, -36, toRadians(0));

    }

    public static class BlueConstants {
        public static Pose2d DUCK_START = new Pose2d(-24, 63, toRadians(-90));
        public static Pose2d CAROUSEL = new Pose2d(-62, 60, toRadians(-90));
        public static Pose2d PARK = new Pose2d(-62, 36, toRadians(0));

    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    public static final Supplier<Pose2d>
            DUCK_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_START, BlueConstants.DUCK_START),
            CAROUSEL_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CAROUSEL, BlueConstants.CAROUSEL),
            DUCK_PARK_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.PARK, BlueConstants.PARK);

    private static int cycles = 0;

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

            DUCK_START_TO_CAROUSEL = b -> b.apply(DUCK_START_SELECT.get())
                    .lineToLinearHeading(CAROUSEL_SELECT.get())
                    .build(),
            DUCK_CAROUSEL_TO_PARK = b -> b.apply(CAROUSEL_SELECT.get())
                    .lineToLinearHeading(DUCK_PARK_SELECT.get())
                    .build();

}


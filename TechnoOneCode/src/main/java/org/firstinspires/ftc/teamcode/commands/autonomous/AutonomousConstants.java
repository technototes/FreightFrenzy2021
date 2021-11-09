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
        public static Pose2d CYCLE_START = new Pose2d(12, -63, toRadians(90));
        public static Pose2d CYCLE_DEPOSIT = new Pose2d(0, -43, toRadians(110));
        public static Pose2d GAP = new Pose2d(30, -64, toRadians(0));
        public static Pose2d CYCLE_COLLECT = new Pose2d(45, -64, toRadians(180));
        public static Pose2d DUCK_START = new Pose2d(-36, -63, toRadians(90));
        public static Pose2d DUCK_DEPOSIT = new Pose2d(-24, -44, toRadians(60));
        public static Pose2d CAROUSEL = new Pose2d(-60, -60, toRadians(0));
        public static Pose2d PARK = new Pose2d(-62, -36, toRadians(0));

    }

    public static class BlueConstants {
        public static Pose2d CYCLE_START = new Pose2d(0, 63, toRadians(-90));
        public static Pose2d CYCLE_DEPOSIT = new Pose2d(0, 44, toRadians(-120));
        public static Pose2d GAP = new Pose2d(30, 64, toRadians(0));
        public static Pose2d CYCLE_COLLECT = new Pose2d(52, 64, toRadians(-180));
        public static Pose2d DUCK_START = new Pose2d(-24, 63, toRadians(-90));
        public static Pose2d DUCK_DEPOSIT = new Pose2d(-24, 44, toRadians(-60));
        public static Pose2d CAROUSEL = new Pose2d(-62, 60, toRadians(-90));
        public static Pose2d PARK = new Pose2d(-62, 36, toRadians(0));

    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    public static final Supplier<Pose2d>
            CYCLE_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CYCLE_START, BlueConstants.CYCLE_START),
            CYCLE_DEPOSIT_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CYCLE_DEPOSIT, BlueConstants.CYCLE_DEPOSIT),
            GAP_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.GAP, BlueConstants.GAP),
            CYCLE_COLLECT_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CYCLE_COLLECT, BlueConstants.CYCLE_COLLECT),
            DUCK_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_START, BlueConstants.DUCK_START),
            DUCK_DEPOSIT_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_DEPOSIT, BlueConstants.DUCK_DEPOSIT),
            CAROUSEL_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CAROUSEL, BlueConstants.CAROUSEL),
            DUCK_PARK_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.PARK, BlueConstants.PARK);

    private static int cycles = 0;

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            CYCLE_START_TO_DEPOSIT = b -> b.apply(CYCLE_START_SELECT.get())
            .lineToLinearHeading(CYCLE_DEPOSIT_SELECT.get())
            .build(),
            CYCLE_DEPOSIT_TO_COLLECT = b -> b.apply(CYCLE_DEPOSIT_SELECT.get())
                    .setReversed(true)
                    .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                    .setVelConstraint((a, e, c, d) -> 10)
                    .lineTo(CYCLE_COLLECT_SELECT.get().vec())
                    .build(),
            CYCLE_COLLECT_TO_DEPOSIT = b -> b.apply(CYCLE_COLLECT_SELECT.get())
                    .setReversed(false)
                    .lineTo(GAP_SELECT.get().vec())
                    .splineTo(CYCLE_DEPOSIT_SELECT.get().vec(), CYCLE_DEPOSIT_SELECT.get().getHeading())
                    .build(),
            DUCK_START_TO_DEPOSIT = b -> b.apply(DUCK_START_SELECT.get())
                    .lineToLinearHeading(DUCK_DEPOSIT_SELECT.get())
                    .build(),
            DUCK_DEPOSIT_TO_CAROUSEL = b -> b.apply(DUCK_DEPOSIT_SELECT.get())
                    .lineToLinearHeading(CAROUSEL_SELECT.get())
                    .build(),
            DUCK_CAROUSEL_TO_PARK = b -> b.apply(CAROUSEL_SELECT.get())
                    .lineToLinearHeading(DUCK_PARK_SELECT.get())
                    .build();

}


package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.util.Alliance;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Math.toRadians;

@Config
public class AutonomousConstants {
    public static class RedConstants {
        public static Pose2d CYCLE_START = new Pose2d(12, -62.8, toRadians(90));
        public static Pose2d CYCLE_DEPOSIT = new Pose2d(-4, -42, toRadians(110));
        public static Pose2d GAP = new Pose2d(30, -63.5, toRadians(0));
        public static Pose2d[] CYCLE_COLLECT = new Pose2d[]{
                new Pose2d(43, -63.5, toRadians(190)),
                new Pose2d(46, -63.5, toRadians(190)),
                new Pose2d(49, -63.5, toRadians(190)),
                new Pose2d(52, -63.5, toRadians(190)),

        };
        public static Pose2d DUCK_START = new Pose2d(-36, -63, toRadians(90));
        public static Pose2d DUCK_DEPOSIT = new Pose2d(-21, -43, toRadians(60));
        public static Pose2d CAROUSEL = new Pose2d(-59, -59, toRadians(0));
        public static Pose2d DUCK_COLLECT_START = new Pose2d(-36, -62, toRadians(45));
        public static Pose2d DUCK_COLLECT_END = new Pose2d(-59, -62, toRadians(45));
        public static Pose2d PARK = new Pose2d(-62, -36, toRadians(0));

    }

    public static class BlueConstants {
        public static Pose2d CYCLE_START = new Pose2d(12, 63, toRadians(-90));
        public static Pose2d CYCLE_DEPOSIT = new Pose2d(-4, 42, toRadians(-110));
        public static Pose2d GAP = new Pose2d(30, 63.5, toRadians(0));
        public static Pose2d[] CYCLE_COLLECT = new Pose2d[]{
                new Pose2d(43, 63.5, toRadians(-190)),
                new Pose2d(46, 63.5, toRadians(-190)),
                new Pose2d(49, 63.5, toRadians(-190)),
                new Pose2d(52, 63.5, toRadians(-190)),

        };
        public static Pose2d DUCK_START = new Pose2d(-36, 63, toRadians(-90));
        public static Pose2d DUCK_DEPOSIT = new Pose2d(-21, 43, toRadians(-60));
        public static Pose2d CAROUSEL = new Pose2d(-65, 59, toRadians(-90));
        public static Pose2d DUCK_COLLECT_START = new Pose2d(-36, 62, toRadians(-45));
        public static Pose2d DUCK_COLLECT_END = new Pose2d(-59, 62, toRadians(-45));
        public static Pose2d PARK = new Pose2d(-68, 36, toRadians(0));

    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    public static final Supplier<Pose2d>
            CYCLE_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CYCLE_START, BlueConstants.CYCLE_START),
            CYCLE_DEPOSIT_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CYCLE_DEPOSIT, BlueConstants.CYCLE_DEPOSIT),
            GAP_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.GAP, BlueConstants.GAP),
            DUCK_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_START, BlueConstants.DUCK_START),
            DUCK_DEPOSIT_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_DEPOSIT, BlueConstants.DUCK_DEPOSIT),
            CAROUSEL_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CAROUSEL, BlueConstants.CAROUSEL),
            DUCK_COLLECT_START_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_COLLECT_START, BlueConstants.DUCK_COLLECT_START),
            DUCK_COLLECT_END_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.DUCK_COLLECT_END, BlueConstants.DUCK_COLLECT_END),
            DUCK_PARK_SELECT = () -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.PARK, BlueConstants.PARK);

    public static final Function<Integer, Pose2d>
            CYCLE_COLLECT_SELECT = i -> Alliance.Selector.selectOf(ALLIANCE, RedConstants.CYCLE_COLLECT[i], BlueConstants.CYCLE_COLLECT[i]);

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            CYCLE_START_TO_DEPOSIT = b -> b.apply(CYCLE_START_SELECT.get())
                    .lineToLinearHeading(CYCLE_DEPOSIT_SELECT.get())
                    .build(),
            DUCK_START_TO_DEPOSIT = b -> b.apply(DUCK_START_SELECT.get())
                    .lineToLinearHeading(DUCK_DEPOSIT_SELECT.get())
                    .build(),
            DUCK_DEPOSIT_TO_CAROUSEL = b -> b.apply(DUCK_DEPOSIT_SELECT.get())
                    .lineToLinearHeading(CAROUSEL_SELECT.get())
                    .build(),
            DUCK_DEPOSIT_TO_PARK = b -> b.apply(DUCK_DEPOSIT_SELECT.get())
                    .lineToLinearHeading(DUCK_PARK_SELECT.get())
                    .build(),
            DUCK_CAROUSEL_TO_INTAKE = b -> b.apply(CAROUSEL_SELECT.get())
                    .lineToLinearHeading(DUCK_COLLECT_START_SELECT.get())
                    .lineToLinearHeading(DUCK_COLLECT_END_SELECT.get())
                    .build(),
            DUCK_INTAKE_TO_DEPOSIT = b -> b.apply(DUCK_COLLECT_END_SELECT.get())
                    .lineToLinearHeading(DUCK_DEPOSIT_SELECT.get())
                    .build();


    public static final BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, Integer, TrajectorySequence>
            CYCLE_DEPOSIT_TO_COLLECT = (b, i) -> b.apply(CYCLE_DEPOSIT_SELECT.get())
                    .setReversed(true)
                    .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                    .setVelConstraint((a, e, c, d) -> 20)
                    .lineToSplineHeading(CYCLE_COLLECT_SELECT.apply(i))
                    .turn(GAP_SELECT.get().getHeading())
                    .build(),
    //TODO add relocalize for purely x
            CYCLE_COLLECT_TO_DEPOSIT = (b, i) -> b.apply(CYCLE_COLLECT_SELECT.apply(i))
                    .setReversed(false)
                    .lineTo(GAP_SELECT.get().vec())
                    .setAccelConstraint((a, e, c, d) -> 30)
                    .splineTo(CYCLE_DEPOSIT_SELECT.get().vec(), CYCLE_DEPOSIT_SELECT.get().getHeading())
                    .build();
}


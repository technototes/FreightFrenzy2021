package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.util.Alliance;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Math.toRadians;

public class RobotConstants {
    @Config
    public static class RedConstants {
        public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, -62.8, toRadians(90));
        public static ConfigurablePose CYCLE_HUB = new ConfigurablePose(-4, -44, toRadians(110));
        public static ConfigurablePose CYCLE_TRENCH = new ConfigurablePose(30, -63.5, toRadians(180));
        public static ConfigurablePose[] WAREHOUSE = new ConfigurablePose[]{
                new ConfigurablePose(43, -63.5, toRadians(190)),
                new ConfigurablePose(46, -63.5, toRadians(190)),
                new ConfigurablePose(49, -63.5, toRadians(190)),
                new ConfigurablePose(52, -63.5, toRadians(190)),

        };

        public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, -63, toRadians(90));
        public static ConfigurablePose DUCK_HUB = new ConfigurablePose(-21, -43, toRadians(60));
        public static ConfigurablePose CAROUSEL = new ConfigurablePose(-60, -59, toRadians(0));
        public static ConfigurablePose DUCK_INTAKE_START = new ConfigurablePose(-20, -62, toRadians(45));
        public static ConfigurablePose DUCK_INTAKE_END = new ConfigurablePose(-59, -62, toRadians(45));
        public static ConfigurablePose SQUARE = new ConfigurablePose(-62, -36, toRadians(0));

        public static ConfigurablePose SHARED_TRENCH = new ConfigurablePose(63.5, -30, toRadians(90));
        public static ConfigurablePose SHARED_HUB = new ConfigurablePose(63.5, -20, toRadians(90));
    }
    @Config
    public static class BlueConstants {
        public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, 63, toRadians(-90));
        public static ConfigurablePose CYCLE_HUB = new ConfigurablePose(-4, 44, toRadians(-110));
        public static ConfigurablePose CYCLE_TRENCH = new ConfigurablePose(30, 63.5, toRadians(-180));
        public static ConfigurablePose[] WAREHOUSE = new ConfigurablePose[]{
                new ConfigurablePose(43, 63.5, toRadians(-190)),
                new ConfigurablePose(46, 63.5, toRadians(-190)),
                new ConfigurablePose(49, 63.5, toRadians(-190)),
                new ConfigurablePose(52, 63.5, toRadians(-190)),

        };

        public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, 63, toRadians(-90));
        public static ConfigurablePose DUCK_HUB = new ConfigurablePose(-21, 43, toRadians(-60));
        public static ConfigurablePose CAROUSEL = new ConfigurablePose(-60, 59, toRadians(-90));
        public static ConfigurablePose DUCK_INTAKE_START = new ConfigurablePose(-20, 62, toRadians(-45));
        public static ConfigurablePose DUCK_INTAKE_END = new ConfigurablePose(-59, 62, toRadians(-45));
        public static ConfigurablePose SQUARE = new ConfigurablePose(-62, 36, toRadians(0));

        public static ConfigurablePose SHARED_TRENCH = new ConfigurablePose(63.5, 30, toRadians(-90));
        public static ConfigurablePose SHARED_HUB = new ConfigurablePose(63.5, 20, toRadians(-90));
    }

    private static Alliance alliance = Alliance.BLUE;

    public static void updateAlliance(Alliance i){
        alliance = i;
    }
    public static Alliance getAlliance(){
        return alliance;
    }

    public static final Supplier<Pose2d>
            CYCLE_START_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.CYCLE_START, BlueConstants.CYCLE_START).toPose(),
            CYCLE_HUB_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.CYCLE_HUB, BlueConstants.CYCLE_HUB).toPose(),
            CYCLE_TRENCH_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.CYCLE_TRENCH, BlueConstants.CYCLE_TRENCH).toPose(),
            DUCK_START_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.DUCK_START, BlueConstants.DUCK_START).toPose(),
            DUCK_HUB_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.DUCK_HUB, BlueConstants.DUCK_HUB).toPose(),
            CAROUSEL_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.CAROUSEL, BlueConstants.CAROUSEL).toPose(),
            DUCK_INTAKE_START_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.DUCK_INTAKE_START, BlueConstants.DUCK_INTAKE_START).toPose(),
            DUCK_INTAKE_END_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.DUCK_INTAKE_END, BlueConstants.DUCK_INTAKE_END).toPose(),
            SQUARE_SELECT = ()->Alliance.Selector.selectOf(alliance, RedConstants.SQUARE, BlueConstants.SQUARE).toPose();

    public static final Function<Integer, Pose2d>
            WAREHOUSE_SELECT = i -> Alliance.Selector.selectOf(alliance, RedConstants.WAREHOUSE[i], BlueConstants.WAREHOUSE[i]).toPose();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            CYCLE_DEPOSIT_PRELOAD = b -> b.apply(CYCLE_START_SELECT.get())
            .lineToLinearHeading(CYCLE_HUB_SELECT.get())
            .build(),
            DUCK_DEPOSIT_PRELOAD = b -> b.apply(DUCK_START_SELECT.get())
                    .lineToLinearHeading(DUCK_HUB_SELECT.get())
                    .build(),
            HUB_TO_CAROUSEL = b -> b.apply(DUCK_HUB_SELECT.get())
                    .lineToLinearHeading(CAROUSEL_SELECT.get())
                    .build(),
            HUB_TO_SQUARE = b -> b.apply(DUCK_HUB_SELECT.get())
                    .lineToLinearHeading(SQUARE_SELECT.get())
                    .build(),
            CAROUSEL_TO_DUCK_INTAKE = b -> b.apply(CAROUSEL_SELECT.get())
                    .lineToLinearHeading(DUCK_INTAKE_START_SELECT.get())
                    .lineToLinearHeading(DUCK_INTAKE_END_SELECT.get())
                    .build(),
            DUCK_INTAKE_TO_HUB = b -> b.apply(DUCK_INTAKE_END_SELECT.get())
                    .lineToLinearHeading(DUCK_HUB_SELECT.get())
                    .build();


    public static final BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, Integer, TrajectorySequence>
            HUB_TO_WAREHOUSE = (b, i) -> b.apply(CYCLE_HUB_SELECT.get())
            .setReversed(true)
            .splineTo(CYCLE_TRENCH_SELECT.get().vec(), 0)
            .setVelConstraint((a, e, c, d) -> 30)
            .lineToSplineHeading(WAREHOUSE_SELECT.apply(i))
            .build();

    public static final BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, Supplier<Pose2d>, TrajectorySequence>
            WAREHOUSE_TO_HUB = (b, p) -> b.apply(new Pose2d(
                Math.max(p.get().getX(), CYCLE_TRENCH_SELECT.get().getX()+1),
                CYCLE_TRENCH_SELECT.get().getY(),
                p.get().getHeading()))
            .lineToSplineHeading(CYCLE_TRENCH_SELECT.get())
            .splineToSplineHeading(CYCLE_HUB_SELECT.get(), CYCLE_HUB_SELECT.get().getHeading())
            .build(),
            WAREHOUSE_TO_SHARED_HUB = (b, p) -> b.apply(new Pose2d(
                    Math.max(p.get().getX(), CYCLE_TRENCH_SELECT.get().getX()+1),
                    CYCLE_TRENCH_SELECT.get().getY(),
                    p.get().getHeading()))
                    .lineToSplineHeading(CYCLE_TRENCH_SELECT.get())
                    .splineToSplineHeading(CYCLE_HUB_SELECT.get(), CYCLE_HUB_SELECT.get().getHeading())
                    .build();


}


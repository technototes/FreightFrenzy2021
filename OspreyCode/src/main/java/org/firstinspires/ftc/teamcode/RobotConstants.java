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
    public static class PoseRedConstants {
        public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, -63, toRadians(90));
        public static ConfigurablePose ALLIANCE_HUB = new ConfigurablePose(2, -53, toRadians(140));
        public static ConfigurablePose CYCLE_TRENCH = new ConfigurablePose(24, -63, toRadians(180));
        public static ConfigurablePose[] AUTO_WAREHOUSE = new ConfigurablePose[]{
                new ConfigurablePose(43, -63, toRadians(190)),
                new ConfigurablePose(46, -63, toRadians(190)),
                new ConfigurablePose(49, -63, toRadians(190)),
                new ConfigurablePose(52, -63, toRadians(190)),
                new ConfigurablePose(55, -63, toRadians(190)),

        };

        public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, -63, toRadians(90));
        public static ConfigurablePose DUCK_HUB = new ConfigurablePose(-26, -53, toRadians(40));
        public static ConfigurablePose CAROUSEL = new ConfigurablePose(-60, -59, toRadians(0));
        public static ConfigurablePose DUCK_INTAKE_START = new ConfigurablePose(-20, -62, toRadians(45));
        public static ConfigurablePose DUCK_INTAKE_END = new ConfigurablePose(-59, -62, toRadians(45));
        public static ConfigurablePose SQUARE = new ConfigurablePose(-62, -36, toRadians(0));

        public static ConfigurablePose SHARED_TRENCH = new ConfigurablePose(63.5, -30, toRadians(90));
        public static ConfigurablePose SHARED_HUB = new ConfigurablePose(63.5, -20, toRadians(90));
    }
    @Config
    public static class PoseBlueConstants {
        public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, 63, toRadians(-90));
        public static ConfigurablePose ALLIANCE_HUB = new ConfigurablePose(2, 53, toRadians(-140));
        public static ConfigurablePose CYCLE_TRENCH = new ConfigurablePose(24, 63, toRadians(-180));
        public static ConfigurablePose[] AUTO_WAREHOUSE = new ConfigurablePose[]{
                new ConfigurablePose(43, 63, toRadians(-190)),
                new ConfigurablePose(46, 63, toRadians(-190)),
                new ConfigurablePose(49, 63, toRadians(-190)),
                new ConfigurablePose(52, 63, toRadians(-190)),
                new ConfigurablePose(55, 63, toRadians(-190)),

        };

        public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, 63, toRadians(-90));
        public static ConfigurablePose DUCK_HUB = new ConfigurablePose(-26, 53, toRadians(-40));
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
            CYCLE_START_SELECT = ()->alliance.selectOf(PoseRedConstants.CYCLE_START, PoseBlueConstants.CYCLE_START).toPose(),
            ALLIANCE_HUB_SELECT = ()->alliance.selectOf(PoseRedConstants.ALLIANCE_HUB, PoseBlueConstants.ALLIANCE_HUB).toPose(),
            SHARED_HUB_SELECT = ()->alliance.selectOf(PoseRedConstants.SHARED_HUB, PoseBlueConstants.SHARED_HUB).toPose(),
            ALLIANCE_TRENCH_SELECT = ()->alliance.selectOf(PoseRedConstants.CYCLE_TRENCH, PoseBlueConstants.CYCLE_TRENCH).toPose(),
            SHARED_TRENCH_SELECT = ()->alliance.selectOf(PoseRedConstants.SHARED_TRENCH, PoseBlueConstants.SHARED_TRENCH).toPose(),
            DUCK_START_SELECT = ()->alliance.selectOf(PoseRedConstants.DUCK_START, PoseBlueConstants.DUCK_START).toPose(),
            DUCK_ALLIANCE_HUB_SELECT = ()->alliance.selectOf(PoseRedConstants.DUCK_HUB, PoseBlueConstants.DUCK_HUB).toPose(),
            CAROUSEL_SELECT = ()->alliance.selectOf(PoseRedConstants.CAROUSEL, PoseBlueConstants.CAROUSEL).toPose(),
            DUCK_INTAKE_START_SELECT = ()->alliance.selectOf(PoseRedConstants.DUCK_INTAKE_START, PoseBlueConstants.DUCK_INTAKE_START).toPose(),
            DUCK_INTAKE_END_SELECT = ()->alliance.selectOf(PoseRedConstants.DUCK_INTAKE_END, PoseBlueConstants.DUCK_INTAKE_END).toPose(),
            SQUARE_SELECT = ()->alliance.selectOf(PoseRedConstants.SQUARE, PoseBlueConstants.SQUARE).toPose();

    public static final Function<Integer, Pose2d>
            CYCLE_INTAKE_SELECT = i ->alliance.selectOf(PoseRedConstants.AUTO_WAREHOUSE[i], PoseBlueConstants.AUTO_WAREHOUSE[i]).toPose();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            CYCLE_DEPOSIT_PRELOAD = b -> b.apply(CYCLE_START_SELECT.get())
            .lineToLinearHeading(ALLIANCE_HUB_SELECT.get())
            .build(),
            DUCK_DEPOSIT_PRELOAD = b -> b.apply(DUCK_START_SELECT.get())
                    .lineToLinearHeading(DUCK_ALLIANCE_HUB_SELECT.get())
                    .build(),
            HUB_TO_CAROUSEL = b -> b.apply(DUCK_ALLIANCE_HUB_SELECT.get())
                    .lineToLinearHeading(CAROUSEL_SELECT.get())
                    .build(),
            HUB_TO_SQUARE = b -> b.apply(DUCK_ALLIANCE_HUB_SELECT.get())
                    .lineToLinearHeading(SQUARE_SELECT.get())
                    .build(),
            CAROUSEL_TO_DUCK_INTAKE = b -> b.apply(CAROUSEL_SELECT.get())
                    .lineToLinearHeading(DUCK_INTAKE_START_SELECT.get())
                    .lineToLinearHeading(DUCK_INTAKE_END_SELECT.get())
                    .build(),
            DUCK_INTAKE_TO_HUB = b -> b.apply(DUCK_INTAKE_END_SELECT.get())
                    .lineToLinearHeading(DUCK_ALLIANCE_HUB_SELECT.get())
                    .build();


    public static final BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, Integer, TrajectorySequence>
            HUB_TO_WAREHOUSE = (b, i) -> b.apply(ALLIANCE_HUB_SELECT.get())
            .setReversed(true)
            .splineTo(ALLIANCE_TRENCH_SELECT.get().vec(), 0)
            .setVelConstraint((a, e, c, d) -> 30)
            .lineToSplineHeading(CYCLE_INTAKE_SELECT.apply(i))
            .build();

    public static final BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, Supplier<Pose2d>, TrajectorySequence>
            WAREHOUSE_TO_HUB = (b, p) -> b.apply(new Pose2d(
                Math.max(p.get().getX(), ALLIANCE_TRENCH_SELECT.get().getX()+1),
                ALLIANCE_TRENCH_SELECT.get().getY(),
                p.get().getHeading()))
            .lineToSplineHeading(ALLIANCE_TRENCH_SELECT.get())
            //.setAccelConstraint((a, e, c, d) -> 30)

            .splineToSplineHeading(ALLIANCE_HUB_SELECT.get(), ALLIANCE_HUB_SELECT.get().getHeading())
            .build(),
            WAREHOUSE_TO_SHARED_HUB = (b, p) -> b.apply(new Pose2d(
                    SHARED_TRENCH_SELECT.get().getX(),
                    p.get().getY(),
                    p.get().getHeading()))
                    .lineToSplineHeading(SHARED_TRENCH_SELECT.get())
                    .splineToSplineHeading(SHARED_HUB_SELECT.get(), SHARED_HUB_SELECT.get().getHeading())
                    .build();
}


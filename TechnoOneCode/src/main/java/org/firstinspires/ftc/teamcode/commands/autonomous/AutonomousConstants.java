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
    @Config
    public static class RedConstants {
        public static Pose2d START = new Pose2d(0, -63, toRadians(90));
        public static Pose2d DEPOSIT = new Pose2d(-1, -39, toRadians(120));
        public static Pose2d GAP = new Pose2d(32, -64.5, toRadians(0));
        public static Pose2d COLLECT = new Pose2d(44, -64.5, toRadians(180));

    }
    @Config
    public static class BlueConstants {
        public static Pose2d START = new Pose2d(0, 63, toRadians(-90));
        public static Pose2d DEPOSIT = new Pose2d(0, 40, toRadians(-120));
        public static Pose2d GAP = new Pose2d(32, 64.5, toRadians(0));
        public static Pose2d COLLECT = new Pose2d(44, 64.5, toRadians(0));

    }

    public static Alliance ALLIANCE = Alliance.RED;

    public static final Supplier<Pose2d>
            START_SELECT = ()->Alliance.Selector.selectOf(ALLIANCE, RedConstants.START, BlueConstants.START),
            DEPOSIT_SELECT = ()->Alliance.Selector.selectOf(ALLIANCE, RedConstants.DEPOSIT, BlueConstants.DEPOSIT),
            GAP_SELECT = ()->Alliance.Selector.selectOf(ALLIANCE, RedConstants.GAP, BlueConstants.GAP),
            COLLECT_SELECT = ()->Alliance.Selector.selectOf(ALLIANCE, RedConstants.COLLECT, BlueConstants.COLLECT);

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            START_TO_DEPOSIT = b -> b.apply(START_SELECT.get())
            .lineToSplineHeading(DEPOSIT_SELECT.get())
            .build(),
            DEPOSIT_TO_COLLECT = b -> b.apply(DEPOSIT_SELECT.get())
                    .setReversed(true)
                    .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                    .lineTo(COLLECT_SELECT.get().vec())
                    .build(),
            COLLECT_TO_DEPOSIT = b -> b.apply(COLLECT_SELECT.get())
                    .setReversed(false)
                    .lineTo(GAP_SELECT.get().vec())
                    .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                    .build();



}


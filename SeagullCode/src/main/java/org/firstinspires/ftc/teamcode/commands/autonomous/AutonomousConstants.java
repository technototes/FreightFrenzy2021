package org.firstinspires.ftc.teamcode.commands.autonomous;

import static com.technototes.path.subsystem.MecanumDrivebaseSubsystem.getAccelerationConstraint;
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
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL3 = new Pose2d(-28, -38, toRadians(-125));
        public static Pose2d DUCK_CAROUSEL = new Pose2d(-64, -60, toRadians(-90));
        public static Pose2d DUCK_PARK = new Pose2d(-70, -33, toRadians(180));
        public static Pose2d DEPOT_START = new Pose2d(0, -66, toRadians(-90));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL3 = new Pose2d(-10, -45, toRadians(-68));
        public static Pose2d DEPOT_GAP = new Pose2d(20, -68, toRadians(0));
        public static Pose2d DEPOT_COLLECT1 = new Pose2d(46, -65, toRadians(0));
        public static Pose2d DEPOT_COLLECT2 = new Pose2d(50, -64, toRadians(0));
    }
    public static class BlueConstants {
        public static Pose2d DUCK_START = new Pose2d(-36, 63, toRadians(90));
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL3 = new Pose2d(-20, 38, toRadians(120));
        public static Pose2d DUCK_CAROUSEL = new Pose2d(-59, 59, toRadians(180));
        public static Pose2d DUCK_PARK = new Pose2d(-62, 34, toRadians(180));
        public static Pose2d DEPOT_START = new Pose2d(0, 66, toRadians(90));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL3 = new Pose2d(1, 45, toRadians(55));
        public static Pose2d DEPOT_GAP = new Pose2d(20, 68, toRadians(0));
        public static Pose2d DEPOT_COLLECT1 = new Pose2d(46, 65, toRadians(0));
        public static Pose2d DEPOT_COLLECT2 = new Pose2d(48, 64, toRadians(0));
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
                        .setAccelConstraint(getAccelerationConstraint(30))
                        .build(),
              RED_DUCK_CAROUSEL_TO_PARK = b -> b.apply(RedConstants.DUCK_CAROUSEL)
                        .lineToLinearHeading(RedConstants.DUCK_PARK)
                        .build(),
              RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DEPOT_START)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .build(),
              RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT1 = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .splineTo(RedConstants.DEPOT_GAP.vec(), RedConstants.DEPOT_GAP.getHeading())
                     //   .setVelConstraint((a, c, d, e)->25)
                        .setAccelConstraint(getAccelerationConstraint(20))
                        .splineTo(RedConstants.DEPOT_COLLECT1.vec(), RedConstants.DEPOT_COLLECT1.getHeading())
                        .build(),
              RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT2 = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .splineTo(RedConstants.DEPOT_GAP.vec(), RedConstants.DEPOT_GAP.getHeading())
                        //.setVelConstraint((a, c, d, e)->25)
                        .splineTo(RedConstants.DEPOT_COLLECT2.vec(), RedConstants.DEPOT_COLLECT2.getHeading())
                        .build(),
              RED_DEPOT_COLLECT1_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DEPOT_COLLECT1)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .splineTo(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3.vec(), RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3.getHeading()+Math.PI)  // add Pi fixed everything
                        .build(),
              RED_DEPOT_COLLECT2_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DEPOT_COLLECT2)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .splineTo(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3.vec(), RedConstants.DEPOT_ALLIANCE_HUB_LEVEL3.getHeading()+Math.PI)
                        .build(),
              /************************************************************************************/
              BLUE_DUCK_CAROUSEL_TO_PARK = b -> b.apply(BlueConstants.DUCK_CAROUSEL)
                        .lineToLinearHeading(BlueConstants.DUCK_PARK)
                        .build(),
              BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_3 = b -> b.apply(BlueConstants.DUCK_START)
                        .lineToLinearHeading(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .build(),
              BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(BlueConstants.DEPOT_START)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL = b -> b.apply(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(BlueConstants.DUCK_CAROUSEL)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT1 = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .splineTo(BlueConstants.DEPOT_GAP.vec(), BlueConstants.DEPOT_GAP.getHeading())
                        .setVelConstraint((a, c, d, e)->20)
                        .splineTo(BlueConstants.DEPOT_COLLECT1.vec(), BlueConstants.DEPOT_COLLECT1.getHeading())
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT2 = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3)
                        .splineTo(BlueConstants.DEPOT_GAP.vec(), BlueConstants.DEPOT_GAP.getHeading())
                        .setVelConstraint((a, c, d, e)->20)
                        .splineTo(BlueConstants.DEPOT_COLLECT2.vec(), BlueConstants.DEPOT_COLLECT2.getHeading())
                        .build(),
              BLUE_DEPOT_COLLECT1_TO_ALLIANCE_HUB_LEVEL_3 = b -> b.apply(BlueConstants.DEPOT_COLLECT1)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .splineTo(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3.vec(), BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3.getHeading()+Math.PI) // may be wrong, need to test out
                        .build(),
              BLUE_DEPOT_COLLECT2_TO_ALLIANCE_HUB_LEVEL_3 = b -> b.apply(BlueConstants.DEPOT_COLLECT2)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .splineTo(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3.vec(), BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL3.getHeading()+Math.PI) // may be wrong, need to test out
                        .build();
}


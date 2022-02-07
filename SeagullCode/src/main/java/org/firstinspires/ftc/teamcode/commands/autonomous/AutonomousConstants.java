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
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL2 = new Pose2d(-28, -46, toRadians(-125));
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL1 = new Pose2d(-28, -52, toRadians(-125));
        public static Pose2d DUCK_CAROUSEL = new Pose2d(-64, -59, toRadians(-90));
        public static Pose2d DUCK_COLLECT1 = new Pose2d(-60, -60, toRadians(-90));
        public static Pose2d DUCK_COLLECT2 = new Pose2d(-48, -61, toRadians(-90));
        public static Pose2d DUCK_PARK = new Pose2d(-69, -36, toRadians(180));
        public static Pose2d DEPOT_START = new Pose2d(0, -66, toRadians(-90));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL3 = new Pose2d(-10, -43, toRadians(-65));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL2 = new Pose2d(-10, -51, toRadians(-68));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL1 = new Pose2d(-4, -56, toRadians(-68));
        public static Pose2d DEPOT_GAP = new Pose2d(20, -66.5, toRadians(0));
        public static Pose2d DEPOT_COLLECT1 = new Pose2d(56, -67, toRadians(0));
        public static Pose2d DEPOT_COLLECT2 = new Pose2d(56, -67, toRadians(0));
    }

    public static class BlueConstants {
        public static Pose2d DUCK_START = new Pose2d(-36, 63, toRadians(90));
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL3 = new Pose2d(-20, 38, toRadians(125));
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL2 = new Pose2d(-20, 46, toRadians(125));
        public static Pose2d DUCK_ALLIANCE_HUB_LEVEL1 = new Pose2d(-20, 52, toRadians(125));

        public static Pose2d DUCK_CAROUSEL = new Pose2d(-59, 59, toRadians(180));
        public static Pose2d DUCK_COLLECT1 = new Pose2d(-55, 60, toRadians(90));
        public static Pose2d DUCK_COLLECT2 = new Pose2d(-43, 61, toRadians(90));
        public static Pose2d DUCK_PARK = new Pose2d(-69, 38, toRadians(180));
        public static Pose2d DEPOT_START = new Pose2d(0, 66, toRadians(90));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL3 = new Pose2d(-10, 42, toRadians(65));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL2 = new Pose2d(-10, 44, toRadians(60));
        public static Pose2d DEPOT_ALLIANCE_HUB_LEVEL1 = new Pose2d(-8, 44, toRadians(60));
        public static Pose2d DEPOT_GAP = new Pose2d(20, 66.5, toRadians(0));
        public static Pose2d DEPOT_COLLECT1 = new Pose2d(56, 67, toRadians(0));
        public static Pose2d DEPOT_COLLECT2 = new Pose2d(58, 67, toRadians(0));
    }

    public static Alliance ALLIANCE = Alliance.BLUE;

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
              // Lists of driving series for auto navigation
              RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DUCK_START)
                        .lineToLinearHeading(RedConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .build(),
              RED_DUCK_ALLIANCE_HUB_LEVEL3_TO_CAROUSEL = b -> b.apply(RedConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .lineToLinearHeading(RedConstants.DUCK_CAROUSEL)
                        .setAccelConstraint(getAccelerationConstraint(30))
                        .build(),
              RED_DUCK_CAROUSEL_TO_DUCK_COLLECT1 = b -> b.apply(RedConstants.DUCK_CAROUSEL)
                        .lineToLinearHeading(RedConstants.DUCK_COLLECT1)
                        .build(),
              RED_DUCK_COLLECT1_TO_COLLECT2 = b -> b.apply(RedConstants.DUCK_COLLECT1)
                      .splineTo(RedConstants.DUCK_COLLECT2.vec(), RedConstants.DUCK_COLLECT2.getHeading())
                      .build(),
              RED_DUCK_COLLECT2_TO_COLLECT1 = b -> b.apply(RedConstants.DUCK_COLLECT2)
                      .splineTo(RedConstants.DUCK_COLLECT1.vec(), RedConstants.DUCK_COLLECT1.getHeading())
                    .build(),
              RED_DUCK_COLLECT2_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(RedConstants.DUCK_COLLECT2)
                      .lineToLinearHeading(RedConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                      .build(),
              RED_DUCK_ALLIANCE_HUB_LEVEL3_TO_PARK = b -> b.apply(RedConstants.DUCK_ALLIANCE_HUB_LEVEL3)
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
    /**
     * -----------------------------Red Middle Level-----------------------
     */
    RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL2 = b -> b.apply(RedConstants.DEPOT_START)
              .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
              .build(),
              RED_ALLIANCE_HUB_LEVEL2_TO_DEPOT_COLLECT1 = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 25)
                        .lineTo(RedConstants.DEPOT_COLLECT1.vec())
                        .build(),
    /*
              RED_ALLIANCE_HUB_LEVEL2_TO_DEPOT_COLLECT2 = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 25)
                        .lineTo(RedConstants.DEPOT_COLLECT2.vec())
                        .build(),
              RED_DEPOT_COLLECT1_TO_ALLIANCE_HUB_LEVEL2 = b -> b.apply(RedConstants.DEPOT_COLLECT1)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .build(),
              RED_DEPOT_COLLECT2_TO_ALLIANCE_HUB_LEVEL2 = b -> b.apply(RedConstants.DEPOT_COLLECT2)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .build(),
     */
              RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL2 = b -> b.apply(RedConstants.DUCK_START)
                        .lineToLinearHeading(RedConstants.DUCK_ALLIANCE_HUB_LEVEL2)
                        .build(),
              RED_DUCK_ALLIANCE_HUB_LEVEL2_TO_CAROUSEL = b -> b.apply(RedConstants.DUCK_ALLIANCE_HUB_LEVEL2)
                        .lineToLinearHeading(RedConstants.DUCK_CAROUSEL)
                        .build(),
    /**
     * -------------Red Low Level----------------------------------------------------------------
     */
    RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL1 = b -> b.apply(RedConstants.DEPOT_START)
              .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
              .build(),
              RED_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT1 = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 25)
                        .lineTo(RedConstants.DEPOT_COLLECT1.vec())
                        .build(),
    /*
              RED_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT2 = b -> b.apply(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 25)
                        .lineTo(RedConstants.DEPOT_COLLECT2.vec())
                        .build(),
              RED_DEPOT_COLLECT1_TO_ALLIANCE_HUB_LEVEL1 = b -> b.apply(RedConstants.DEPOT_COLLECT1)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .build(),
              RED_DEPOT_COLLECT2_TO_ALLIANCE_HUB_LEVEL1 = b -> b.apply(RedConstants.DEPOT_COLLECT2)
                        .lineToLinearHeading(RedConstants.DEPOT_GAP)
                        .lineToLinearHeading(RedConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .build(),
     */
              RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL1 = b -> b.apply(RedConstants.DUCK_START)
                        .lineToLinearHeading(RedConstants.DUCK_ALLIANCE_HUB_LEVEL1)
                        .build(),
              RED_DUCK_ALLIANCE_HUB_LEVEL1_TO_CAROUSEL = b -> b.apply(RedConstants.DUCK_ALLIANCE_HUB_LEVEL1)
                        .lineToLinearHeading(RedConstants.DUCK_CAROUSEL)
                        .build(),

    /************************************************************************************/
    /*
    BLUE_DUCK_CAROUSEL_TO_PARK = b -> b.apply(BlueConstants.DUCK_CAROUSEL)
              .lineToLinearHeading(BlueConstants.DUCK_PARK)
              .build(),
     */
              BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_3 = b -> b.apply(BlueConstants.DUCK_START)
                        .lineToLinearHeading(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                        .build(),
              BLUE_DUCK_CAROUSEL_TO_DUCK_COLLECT1 = b -> b.apply(BlueConstants.DUCK_CAROUSEL)
                              .lineToLinearHeading(BlueConstants.DUCK_COLLECT1)
                              .build(),

              BLUE_DUCK_COLLECT1_TO_COLLECT2 = b -> b.apply(BlueConstants.DUCK_COLLECT1)
                              .splineTo(BlueConstants.DUCK_COLLECT2.vec(), BlueConstants.DUCK_COLLECT2.getHeading())
                              .build(),
              BLUE_DUCK_COLLECT2_TO_COLLECT1 = b -> b.apply(BlueConstants.DUCK_COLLECT2)
                              .splineTo(BlueConstants.DUCK_COLLECT1.vec(), BlueConstants.DUCK_COLLECT2.getHeading())
                              .build(),
              BLUE_DUCK_COLLECT2_TO_ALLIANCE_HUB_LEVEL3 = b -> b.apply(BlueConstants.DUCK_COLLECT2)
                              .lineToLinearHeading(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                              .build(),
              BLUE_DUCK_ALLIANCE_HUB_LEVEL3_TO_PARK = b -> b.apply(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL3)
                              .lineToLinearHeading(BlueConstants.DUCK_PARK)
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
                        .build(),
    /**
     * ------------------------- Blue Middle Level-----------------------------------------------
     */
    BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_2 = b -> b.apply(BlueConstants.DUCK_START)
              .lineToLinearHeading(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL2)
              .build(),
              BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL2 = b -> b.apply(BlueConstants.DEPOT_START)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL2_TO_CAROUSEL = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .lineToLinearHeading(BlueConstants.DUCK_CAROUSEL)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL2_TO_DEPOT_COLLECT1 = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 20)
                        .lineToLinearHeading(BlueConstants.DEPOT_COLLECT1)
                        .build(),
    /*
              BLUE_ALLIANCE_HUB_LEVEL2_TO_DEPOT_COLLECT2 = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 20)
                        .lineToLinearHeading(BlueConstants.DEPOT_COLLECT2)
                        .build(),
              BLUE_DEPOT_COLLECT1_TO_ALLIANCE_HUB_LEVEL_2 = b -> b.apply(BlueConstants.DEPOT_COLLECT1)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .build(),
              BLUE_DEPOT_COLLECT2_TO_ALLIANCE_HUB_LEVEL_2 = b -> b.apply(BlueConstants.DEPOT_COLLECT2)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL2)
                        .build(),
     */

    /**
     * --------------------Blue Lower Level-----------------------------------------------
     */
    BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_1 = b -> b.apply(BlueConstants.DUCK_START)
              .lineToLinearHeading(BlueConstants.DUCK_ALLIANCE_HUB_LEVEL1)
              .build(),
              BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL1 = b -> b.apply(BlueConstants.DEPOT_START)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL1_TO_CAROUSEL = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .lineToLinearHeading(BlueConstants.DUCK_CAROUSEL)
                        .build(),
              BLUE_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT1 = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 20)
                        .lineToLinearHeading(BlueConstants.DEPOT_COLLECT1)
                        .build();
    /*
              BLUE_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT2 = b -> b.apply(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .setVelConstraint((a, c, d, e) -> 20)
                        .lineToLinearHeading(BlueConstants.DEPOT_COLLECT2)
                        .build(),
              BLUE_DEPOT_COLLECT1_TO_ALLIANCE_HUB_LEVEL_1 = b -> b.apply(BlueConstants.DEPOT_COLLECT1)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .build(),
              BLUE_DEPOT_COLLECT2_TO_ALLIANCE_HUB_LEVEL_1 = b -> b.apply(BlueConstants.DEPOT_COLLECT2)
                        .lineToLinearHeading(BlueConstants.DEPOT_GAP)
                        .lineToLinearHeading(BlueConstants.DEPOT_ALLIANCE_HUB_LEVEL1)
                        .build();
     */
}


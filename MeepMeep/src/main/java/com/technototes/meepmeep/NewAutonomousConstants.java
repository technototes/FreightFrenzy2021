package com.technototes.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Math.toRadians;

public class NewAutonomousConstants {

    public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, 63, toRadians(-90));
    public static ConfigurablePose CYCLE_DEPOSIT = new ConfigurablePose(-4, 44, toRadians(-110));
    public static ConfigurablePose GAP = new ConfigurablePose(30, 63.5, toRadians(0));
    public static ConfigurablePose CYCLE_COLLECT = new ConfigurablePose(40, 63.5, toRadians(-180));

    public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, 63, toRadians(-90));
    public static ConfigurablePose DUCK_DEPOSIT = new ConfigurablePose(-21, 43, toRadians(-60));
    public static ConfigurablePose CAROUSEL = new ConfigurablePose(-60, 59, toRadians(-90));
    public static ConfigurablePose DUCK_COLLECT_START = new ConfigurablePose(-20, 62, toRadians(-45));
    public static ConfigurablePose DUCK_COLLECT_END = new ConfigurablePose(-59, 62, toRadians(-45));
    public static ConfigurablePose PARK = new ConfigurablePose(-62, 36, toRadians(0));


    public static Alliance alliance = Alliance.BLUE;

    public static void updateAlliance(Alliance newAlliance){
        if(alliance == newAlliance) return;
        CYCLE_START.mutatePose(ConfigurablePose::mirrorOverX);
        CYCLE_DEPOSIT.mutatePose(ConfigurablePose::mirrorOverX);
        GAP.mutatePose(ConfigurablePose::mirrorOverX);
        CYCLE_COLLECT.mutatePose(ConfigurablePose::mirrorOverX);
        DUCK_START.mutatePose(ConfigurablePose::mirrorOverX);
        DUCK_DEPOSIT.mutatePose(ConfigurablePose::mirrorOverX);
        CAROUSEL.mutatePose(ConfigurablePose::mirrorOverX).mutateHeading(h->h+toRadians(newAlliance==Alliance.RED ? -90 : 90));
        DUCK_COLLECT_START.mutatePose(ConfigurablePose::mirrorOverX);
        DUCK_COLLECT_END.mutatePose(ConfigurablePose::mirrorOverX);
        PARK.mutatePose(ConfigurablePose::mirrorOverX);
        alliance = newAlliance;
    }

    public static double MAX_VEL = 50;
    public static double MAX_ACCEL = 40;
    public static double MAX_ANG_VEL = Math.toRadians(180);
    public static double MAX_ANG_ACCEL = Math.toRadians(120);
    public static double TRACK_WIDTH = 9.5;

    public static MinVelocityConstraint MIN_VEL = new MinVelocityConstraint(Arrays.asList(
            new AngularVelocityConstraint(MAX_ANG_VEL),
            new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
    ));
    public static ProfileAccelerationConstraint PROF_ACCEL = new ProfileAccelerationConstraint(MAX_ACCEL);


    public static Function<Pose2d, TrajectoryBuilder> function = pose -> new TrajectoryBuilder(pose, MIN_VEL, PROF_ACCEL);
    public static BiFunction<Pose2d, Boolean, TrajectoryBuilder> bifunction = (pose, reversed) -> new TrajectoryBuilder(pose, reversed, MIN_VEL, PROF_ACCEL);


    public static Supplier<Trajectory>
            CYCLE_START_TO_DEPOSIT = ()->function.apply(CYCLE_START.toPose())
                    .lineToLinearHeading(CYCLE_DEPOSIT.toPose())
                    .build(),
            DUCK_START_TO_DEPOSIT = ()-> function.apply(DUCK_START.toPose())
                    .lineToLinearHeading(DUCK_DEPOSIT.toPose())
                    .build(),
            DUCK_DEPOSIT_TO_CAROUSEL = ()->function.apply(DUCK_DEPOSIT.toPose())
                    .lineToLinearHeading(CAROUSEL.toPose())
                    .build(),
            DUCK_DEPOSIT_TO_PARK = ()->function.apply(DUCK_DEPOSIT.toPose())
                    .lineToLinearHeading(PARK.toPose())
                    .build(),
            DUCK_CAROUSEL_TO_INTAKE = ()->function.apply(CAROUSEL.toPose())
                    .lineToLinearHeading(DUCK_COLLECT_START.toPose())
                    .lineToLinearHeading(DUCK_COLLECT_END.toPose())
                    .build(),
            DUCK_INTAKE_TO_DEPOSIT = ()->function.apply(DUCK_COLLECT_END.toPose())
                    .lineToLinearHeading(DUCK_DEPOSIT.toPose())
                    .build(),
            CYCLE_DEPOSIT_TO_COLLECT = ()->bifunction.apply(CYCLE_DEPOSIT.toPose(), true)
                    .splineTo(GAP.toVec(), GAP.getHeading())
                    .lineToSplineHeading(CYCLE_COLLECT.mutateX(x->x+3).toPose())
                    .build();

    public static Function<Supplier<Pose2d>, Trajectory>
//            CYCLE_COLLECT_TO_DEPOSIT = i -> function.apply(GAP.mapPose(p->new Pose2d(
//                Math.max(i.get().getX(), p.getX()),
//                p.getY(),
//                i.get().getHeading())))
            CYCLE_COLLECT_TO_DEPOSIT = i -> function.apply(CYCLE_COLLECT.toPose())
            .lineTo(GAP.toVec())
            .splineToSplineHeading(CYCLE_DEPOSIT.toPose(), CYCLE_DEPOSIT.getHeading())
            .build();


}


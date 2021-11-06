package com.technototes.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.technototes.meepmeep.AutonomousConstants.*;

public class MeepMeepTesting {

    public static void main(String[] args) {
        MeepMeep mm = new MeepMeep(800)
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_FREIGHT_FRENZY)
                // Set theme
                .setTheme(new ColorSchemeRedDark())
                // Background opacity from 0-1
                .setBackgroundAlpha(1f)
                .setBotDimensions(11.2, 12.2)
                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 45, Math.toRadians(180), Math.toRadians(80), 10.5)

                //88 pts from preload 4 cycles and park
                //62 pts from preload all duck and park
                //86 pts from preload all duck 2 cycles and park
                //maybe 98 with 3 cycles
                .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-24, -65, Math.toRadians(90)))
//                                        .lineToLinearHeading(new Pose2d(-24, -40, Math.toRadians(60)))
//                                        .lineToLinearHeading(new Pose2d(-60, -60, Math.toRadians(90)))
//                                        .waitSeconds(2)
//                                        .setTangent(Math.toRadians(30))
//                                        .splineToLinearHeading(new Pose2d(-20, -64.5, Math.toRadians(0)), Math.toRadians(-40))
//                                        .strafeTo(new Vector2d(-50, -64.5))
//                                        .lineToLinearHeading(new Pose2d(-24, -40, Math.toRadians(60)))
//                                        .lineToLinearHeading(new Pose2d(-60, -37, Math.toRadians(90)))
//                                        .build()

                                drive.trajectorySequenceBuilder(START_SELECT.get())
                                        .lineToSplineHeading(DEPOSIT_SELECT.get())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .setReversed(false)
                                        .lineTo(GAP_SELECT.get().vec())
                                        .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .setReversed(false)
                                        .lineTo(GAP_SELECT.get().vec())
                                        .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .setReversed(false)
                                        .lineTo(GAP_SELECT.get().vec())
                                        .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .setReversed(false)
                                        .lineTo(GAP_SELECT.get().vec())
                                        .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .setReversed(false)
                                        .lineTo(GAP_SELECT.get().vec())
                                        .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .setReversed(false)
                                        .lineTo(GAP_SELECT.get().vec())
                                        .splineTo(DEPOSIT_SELECT.get().vec(), DEPOSIT_SELECT.get().getHeading())
                                        .setReversed(true)
                                        .splineTo(GAP_SELECT.get().vec(), GAP_SELECT.get().getHeading())
                                        .lineTo(COLLECT_SELECT.get().vec())
                                        .build()


//                        COLLECT_TO_DEPOSIT.apply(drive::trajectorySequenceBuilder)
//                                drive.trajectorySequenceBuilder(new Pose2d(-10, -65, Math.toRadians(90)))
//                                        .lineToSplineHeading(new Pose2d(-10, -55, Math.toRadians(130)))
//                                        .splineToConstantHeading(new Vector2d(0, -40), Math.toRadians(-50))
////                                        .setTangent(Math.toRadians(-60))
//                                        .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
//                                        .strafeTo(new Vector2d(44, -64.5))
////                                        .setReversed(false)
//                                        .lineToSplineHeading(new Pose2d(20, -64.5, Math.toRadians(130)))
//                                        .splineToConstantHeading(new Vector2d(0, -40), Math.toRadians(50))
////                                        .setReversed(true)
////                                        .setTangent(Math.toRadians(50))
//
//                                        .splineTo (new Vector2d(30, -64.5), Math.toRadians(0))
//                                        .strafeTo(new Vector2d(46, -64.5))
//                                        .setReversed(false)
//                                        .strafeTo(new Vector2d(30, -64.5))
//                                        .splineTo(new Vector2d(0, -40), Math.toRadians(120))
//                                        .setReversed(true)
//                                        .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
//                                        .strafeTo(new Vector2d(48, -64.5))
//                                        .setReversed(false)
//                                        .strafeTo(new Vector2d(30, -64.5))
//                                        .splineTo(new Vector2d(0, -40), Math.toRadians(120))
//                                        .setReversed(true)
//                                        .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
//                                        .strafeTo(new Vector2d(50, -64.5))
//                                        .setReversed(false)
//                                        .strafeTo(new Vector2d(30, -64.5))
//                                        .splineTo(new Vector2d(0, -40), Math.toRadians(120))
//                                        .setReversed(true)
//                                        .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
//                                        .strafeTo(new Vector2d(35, -64.5))
//                                        .build()
                )


                .start();
    }
}

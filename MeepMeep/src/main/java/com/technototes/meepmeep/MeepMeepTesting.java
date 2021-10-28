package com.technototes.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

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
                .setConstraints(50, 25, Math.toRadians(80), Math.toRadians(40), 11)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, -65, Math.toRadians(90)))
                                .lineToSplineHeading(new Pose2d(0, -40, Math.toRadians(120)))
                                .setReversed(true)
                                .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
                                .strafeTo(new Vector2d(44, -64.5))
                                .setReversed(false)
                                .strafeTo(new Vector2d(30, -64.5))
                                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
                                .strafeTo(new Vector2d(46, -64.5))
                                .setReversed(false)
                                .strafeTo(new Vector2d(30, -64.5))
                                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
                                .strafeTo(new Vector2d(48, -64.5))
                                .setReversed(false)
                                .strafeTo(new Vector2d(30, -64.5))
                                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
                                .strafeTo(new Vector2d(50, -64.5))
                                .setReversed(false)
                                .strafeTo(new Vector2d(30, -64.5))
                                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(30, -64.5), Math.toRadians(0))
                                .strafeTo(new Vector2d(35, -64.5))
                                .build()
                )


                .start();
    }
}

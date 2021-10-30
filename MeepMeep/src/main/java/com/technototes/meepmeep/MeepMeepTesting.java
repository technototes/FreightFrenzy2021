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
                .setBotDimensions(12, 12)
                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 30, Math.toRadians(60), Math.toRadians(60), 11)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -65, Math.toRadians(90)))
                                .splineTo(new Vector2d(-5, -40), Math.toRadians(130))
                                .setReversed(true)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(0))
                                .strafeTo(new Vector2d(45, -65))
                                .setReversed(false)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(180))
                                .splineTo(new Vector2d(-5, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(0))
                                .strafeTo(new Vector2d(47, -65))
                                .setReversed(false)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(180))
                                .splineTo(new Vector2d(-5, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(0))
                                .strafeTo(new Vector2d(49, -65))
                                .setReversed(false)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(180))
                                .splineTo(new Vector2d(-5, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(0))
                                .strafeTo(new Vector2d(51, -65))
                                .setReversed(false)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(180))
                                .splineTo(new Vector2d(-5, -40), Math.toRadians(120))
                                .setReversed(true)
                                .splineTo(new Vector2d(35, -65), Math.toRadians(0))
                                .strafeTo(new Vector2d(40, -65))
                                .build()
                )


                .start();
    }
}

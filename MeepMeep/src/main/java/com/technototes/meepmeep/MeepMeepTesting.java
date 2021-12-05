package com.technototes.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import static com.technototes.meepmeep.NewAutonomousConstants.*;

public class  MeepMeepTesting {

    public static void main(String[] args) {
        updateAlliance(Alliance.RED);
        MeepMeep mm = new MeepMeep(800, 120);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(mm)
                .setConstraints(50, 40, Math.toRadians(180), Math.toRadians(120), 9.5)
                .setDimensions(11.6, 12.2)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive->drive.trajectorySequenceBuilder(CYCLE_START.toPose())
                        .addTrajectory(CYCLE_START_TO_DEPOSIT.get())
                        .addTrajectory(CYCLE_DEPOSIT_TO_COLLECT.get())
                        .addTrajectory(CYCLE_COLLECT_TO_DEPOSIT.apply(drive::getPoseEstimate))
                        .addTrajectory(CYCLE_DEPOSIT_TO_COLLECT.get())
                        .addTrajectory(CYCLE_COLLECT_TO_DEPOSIT.apply(drive::getPoseEstimate))
                        .addTrajectory(CYCLE_DEPOSIT_TO_COLLECT.get())
                        .addTrajectory(CYCLE_COLLECT_TO_DEPOSIT.apply(drive::getPoseEstimate))
                        .addTrajectory(CYCLE_DEPOSIT_TO_COLLECT.get())
                        .addTrajectory(CYCLE_COLLECT_TO_DEPOSIT.apply(drive::getPoseEstimate))
                        .addTrajectory(CYCLE_DEPOSIT_TO_COLLECT.get())
                        .build()
                );


        mm
                .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setTheme(new ColorSchemeRedDark())
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}

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

import org.firstinspires.ftc.teamcode.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmInCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmSharedCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightOutCommand;
import org.firstinspires.ftc.teamcode.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel2Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.teamcode.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class RobotConstants {


    @Config
    public static class AutoRedConstants {
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
    public static class AutoBlueConstants {
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
            CYCLE_START_SELECT = ()->alliance.selectOf(AutoRedConstants.CYCLE_START, AutoBlueConstants.CYCLE_START).toPose(),
            ALLIANCE_HUB_SELECT = ()->alliance.selectOf(AutoRedConstants.ALLIANCE_HUB, AutoBlueConstants.ALLIANCE_HUB).toPose(),
            SHARED_HUB_SELECT = ()->alliance.selectOf(AutoRedConstants.SHARED_HUB, AutoBlueConstants.SHARED_HUB).toPose(),
            ALLIANCE_TRENCH_SELECT = ()->alliance.selectOf(AutoRedConstants.CYCLE_TRENCH, AutoBlueConstants.CYCLE_TRENCH).toPose(),
            SHARED_TRENCH_SELECT = ()->alliance.selectOf(AutoRedConstants.SHARED_TRENCH, AutoBlueConstants.SHARED_TRENCH).toPose(),
            DUCK_START_SELECT = ()->alliance.selectOf(AutoRedConstants.DUCK_START, AutoBlueConstants.DUCK_START).toPose(),
            DUCK_ALLIANCE_HUB_SELECT = ()->alliance.selectOf(AutoRedConstants.DUCK_HUB, AutoBlueConstants.DUCK_HUB).toPose(),
            CAROUSEL_SELECT = ()->alliance.selectOf(AutoRedConstants.CAROUSEL, AutoBlueConstants.CAROUSEL).toPose(),
            DUCK_INTAKE_START_SELECT = ()->alliance.selectOf(AutoRedConstants.DUCK_INTAKE_START, AutoBlueConstants.DUCK_INTAKE_START).toPose(),
            DUCK_INTAKE_END_SELECT = ()->alliance.selectOf(AutoRedConstants.DUCK_INTAKE_END, AutoBlueConstants.DUCK_INTAKE_END).toPose(),
            SQUARE_SELECT = ()->alliance.selectOf(AutoRedConstants.SQUARE, AutoBlueConstants.SQUARE).toPose();

    public static final Function<Integer, Pose2d>
            CYCLE_INTAKE_SELECT = i ->alliance.selectOf(AutoRedConstants.AUTO_WAREHOUSE[i], AutoBlueConstants.AUTO_WAREHOUSE[i]).toPose();

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
            .setAccelConstraint((a, e, c, d) -> 30)
            .splineTo(ALLIANCE_TRENCH_SELECT.get().vec(), 0)
            .setAccelConstraint((a, e, c, d) -> 60)
            .setVelConstraint((a, e, c, d)->70)
            .lineToSplineHeading(CYCLE_INTAKE_SELECT.apply(i))
            .build();

    public static final BiFunction<Function<Pose2d, TrajectorySequenceBuilder>, Supplier<Pose2d>, TrajectorySequence>
            WAREHOUSE_TO_HUB = (b, p) -> b.apply(new Pose2d(
                Math.max(p.get().getX(), ALLIANCE_TRENCH_SELECT.get().getX()+1),
                ALLIANCE_TRENCH_SELECT.get().getY(),
                p.get().getHeading()))
            .lineToSplineHeading(ALLIANCE_TRENCH_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 30)

            .splineToSplineHeading(ALLIANCE_HUB_SELECT.get(), ALLIANCE_HUB_SELECT.get().getHeading())
            .build(),
            WAREHOUSE_TO_SHARED_HUB = (b, p) -> b.apply(new Pose2d(
                    SHARED_TRENCH_SELECT.get().getX(),
                    p.get().getY(),
                    p.get().getHeading()))
                    .lineToSplineHeading(SHARED_TRENCH_SELECT.get())
                    .splineToSplineHeading(SHARED_HUB_SELECT.get(), SHARED_HUB_SELECT.get().getHeading())
                    .build();


    private static AllianceHubStrategy allianceHubStrategy;
    private static SharedHubStrategy sharedHubStrategy;

    public static AllianceHubStrategy getAllianceStrategy() {
        return allianceHubStrategy;
    }

    public static SharedHubStrategy getSharedStrategy() {
        return sharedHubStrategy;
    }

    public static void setStrategy(AllianceHubStrategy allianceHubStrategy, SharedHubStrategy sharedHubStrategy) {
        RobotConstants.allianceHubStrategy = allianceHubStrategy;
        RobotConstants.sharedHubStrategy = sharedHubStrategy;
    }

    public enum AllianceHubStrategy {
        HIGH, MID;
    }

    public enum SharedHubStrategy {
        OWN, STEAL;
    }
}


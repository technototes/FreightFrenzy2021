package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

@Config
public class AutoState {

    //ALL VALUES ARE FOR RED, THEY CORRECT FOR BLUE LATER ON
    public static final double ADDITIONAL_DELAY_BETWEEN_SHOTS = 0.1;

    public static final double INITIAL_SHOOTING_ROTATION = -12;
    public static final double INITIAL_FLAP_ANGLE = 0.2;

    public static final double SECONDARY_SHOOTING_X = 40;
    public static final double SECONDARY_SHOOTING_Y = 0;
    public static final double SECONDARY_SHOOTING_TANGENT = 0;
    public static final double SECONDARY_SHOOTING_ROTATION = -15;

    public static final double SECONDARY_FLAP_ANGLE = 0.3;

    public static final double FIRST_WOBBLE_DROP_X_0 = 65;
    public static final double FIRST_WOBBLE_DROP_Y_0 = -8;
    public static final double FIRST_WOBBLE_DROP_ROTATION_0 = 90;
    public static final double FIRST_WOBBLE_DROP_X_1 = 87;
    public static final double FIRST_WOBBLE_DROP_Y_1 = 16;
    public static final double FIRST_WOBBLE_DROP_ROTATION_1 = 90;
    public static final double FIRST_WOBBLE_DROP_X_4 = 112;
    public static final double FIRST_WOBBLE_DROP_Y_4 = -8;
    public static final double FIRST_WOBBLE_DROP_ROTATION_4 = 90;

    public static final double SECOND_WOBBLE_DROP_OFFSET_X = 5;
    public static final double SECOND_WOBBLE_DROP_OFFSET_Y = 5;


    public static final double SECOND_WOBBLE_GRAB_X = 28;
    public static final double SECOND_WOBBLE_GRAB_Y = 32;
    public static final double SECOND_WOBBLE_GRAB_ROTATION = 0;
    public static final double SECOND_WOBBLE_GRAB_TAN = -90;


    public static final double END_X = 70;
    public static final double END_Y = 10;
    public static final double END_ROTATION = 90;


    public enum StackSize{
        ZERO(0, FIRST_WOBBLE_DROP_X_0, FIRST_WOBBLE_DROP_Y_0, FIRST_WOBBLE_DROP_ROTATION_0),
        ONE(1, FIRST_WOBBLE_DROP_X_1, FIRST_WOBBLE_DROP_Y_1, FIRST_WOBBLE_DROP_ROTATION_1),
        FOUR(4, FIRST_WOBBLE_DROP_X_4, FIRST_WOBBLE_DROP_Y_4, FIRST_WOBBLE_DROP_ROTATION_4),
        NONE(0, 0, 0, 0);

        public int numRings;
        public double xPos, yPos, rotation;

        StackSize(int num, double x, double y, double r) {
            numRings = num;
            xPos = x;
            yPos = y;
            rotation = r;
        }
    }
    public enum Team{
        BLUE(true), RED(false), NONE(false);
        boolean flip;
        Team(boolean b) {
            flip = b;
        }
    }

    public StackSize stackSize;
    public Team team;

    public AutoState(Team t){
        team = t;
        stackSize = StackSize.NONE;
    }

    public void setStackSize(StackSize s){
        stackSize = s;
    }

    public Pose2d getWobbleDropPosition(){
        return new Pose2d(stackSize.xPos, stackSize.yPos, stackSize.rotation);
    }

    public Pose2d correctedFirstWobbleDropPos(){
        return correctedPos(stackSize.xPos, stackSize.yPos, stackSize.rotation);
    }
    public Pose2d correctedSecondWobbleDropPos(){
        return correctedPos(stackSize.xPos-SECOND_WOBBLE_DROP_OFFSET_X, stackSize.yPos+SECOND_WOBBLE_DROP_OFFSET_Y, stackSize.rotation);
    }
    public Pose2d correctedSecondWobbleGrabPos(){
        return correctedPos(SECOND_WOBBLE_GRAB_X, SECOND_WOBBLE_GRAB_Y, SECOND_WOBBLE_GRAB_ROTATION);
    }

    public Pose2d correctedEndPos(){
        return correctedPos(END_X, END_Y, END_ROTATION);
    }


    public Pose2d correctedPos(double x, double y, double r){
        return new Pose2d(x, corrected(y), corrected(r));
    }

    public double correctedTan(double t){
        return corrected(t);
    }

    public double corrected(double v){
        return team.flip ? -v : v;
    }

    public int getNumRings(){
        return stackSize.numRings;
    }
}
